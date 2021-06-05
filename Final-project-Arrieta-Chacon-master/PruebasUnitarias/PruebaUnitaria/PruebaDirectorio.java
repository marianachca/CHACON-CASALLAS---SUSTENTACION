package PruebaUnitaria;

import ParoNacional.Directorio;
import ParoNacional.Victima;
import junit.framework.TestCase;


/**
 * Clase usada para verificar el correcto funcionamiento de los metodos de la clase Directorio
 */
public class PruebaDirectorio  extends TestCase
{
    /**
     * Directorio de prueba
     */
    private Directorio directorio;

    /**
     * Escenario que crea un objeto directorio con la ruta del archivo a persistir
     */
    public void escenario()
    {
        directorio = new Directorio("ArchivoPersistencia/Directorio.csv");
    }

    /**
     * Este metodo verifica que se puede agregar una victimas a la base de datos
     */
    public void testAgregarVictima()
    {
        escenario();
        //Verifica que al iniciar la aplicacion por primera vez la base de datos este vacia.
        assertEquals("La base de datos deberia estar vacia",directorio.darVictimas().size(), 0);

        //Se agrega solo una victima a la base de datos.
        directorio.agregarVictima(new Victima("pepe", "perez", "1000", "Bogota", "Fue secuestrado por la policia mientras marchaba de forma pacifica",  "35"));
        assertEquals("Debe existir solo UNA victima en la base de datos", directorio.darVictimas().size(), 1);

        //Verifica que los atributos de la victima se hayan ingresado correctamente
        assertEquals("El nombre NO se guard� correctamente",directorio.darVictimas().get(0).darNombres(), "pepe");
        assertEquals("El apellido NO se guard� correctamente",directorio.darVictimas().get(0).darApellidos(), "perez");
        assertEquals("La ciudad de origen NO se guard� correctamente",directorio.darVictimas().get(0).darCiudadOrigen(), "Bogota");
        assertEquals("Los hechos NO se guard� correctamente",directorio.darVictimas().get(0).darHechos(), "Fue secuestrado por la policia mientras marchaba de forma pacifica");

        //Agregar una segunda victima a la base de datos.
        directorio.agregarVictima(new Victima("lolita", "luna", "1001", "cali", "Fue asesina por policias despues de haber sido abusada sexualmente", "40"));
        assertEquals("Deben existir DOS victimas en la base de datos", directorio.darVictimas().size(), 2);
    }

    private String fechaNacimiento(String edad) {

        return null;

    }

    /**
     * Este metodo verifica que se puede buscar una victima, siempre y cuando se encuentre en la base de datos, si la victima no est� se lleva a cabo una excepcion
     */
    public void testBuscarVictima()
    {
        escenario();
        //Se llama el metodo de prueba de agregar para tener victimas en la base de datos para buscar. Si no se encuentra se lanza excepcion
        testAgregarVictima();

        try
        {
            //Se busca la victima en la base de datos para verificar que exista
            Victima buscada = directorio.buscarVictima("lolita", "luna");
            assertNotNull(buscada);
        }
        catch(Exception e)
        {
            fail("No debe fallar el metodo");
        }

        try
        {
            //Se busca una victima que no est� en la base de datos. DEBE lanzar excepcion
            Victima buscada = directorio.buscarVictima("nadie", "ninguno");
            assertNull("No deberia encontrar una victima con ese nombre y apellido porque no existe en la base de datos", buscada);
        }
        catch (Exception e)
        {
            //Debe llegar aqu� el metodo
        }
    }

    /**
     * Este metodo verifica que se pueda editar una vitima dentro de la base de datos.
     */
    public void testEditarVictima()
    {
        escenario();
        testAgregarVictima();

        try
        {
            //Se edita una victima existente. Si no existe se lanza excepcion.
            directorio.editarVictima("pepe", "perez", "pepe pepito", "perez perea", "ibague", "Fue secuestrado por la policia mientras marchaba de forma pacifica");

            Victima editada = directorio.darVictimas().get(0);

            //Verifica que los campos editados se hayan modificado en el sistema correctamente
            assertEquals("El nombre NO se guard� correctamente", editada.darNombres(), "pepe pepito");
            assertEquals("El apellido NO se guard� correctamente", editada.darApellidos(), "perez perea");
            assertEquals("La ciudad de origen NO se guard� correctamente", editada.darCiudadOrigen(), "ibague");
            assertEquals("Los hechos NO se guard� correctamente", editada.darHechos(), "Fue secuestrado por la policia mientras marchaba de forma pacifica");

        }
        catch (Exception e)
        {
            fail("No debe fallar la edicion de la victima en la base de datos porque SI se encuentra en la base de datos");
        }
    }

    /**
     * Este metodo verifica que se pueda eliminar una victima de la base de datos
     */
    public void testEliminar()
    {
        escenario();
        testAgregarVictima();

        try
        {
            //se elimina la victima encontrada dentro de la base datos
            directorio.eliminarVictima("pepe", "perez");
            assertEquals("No se elimina la victima",directorio.darVictimas().size() , 1);

        }
        catch(Exception e)
        {
            fail("No debe fallar el metodo pues la vixtima SI existe en la base de datos");
        }

        try
        {
            //Se intenta eliminar una victima que no existe en la base de datos por lo tanto se debe generar una excepcion.
            directorio.eliminarVictima("nadie", "nadie");
            assertEquals("No deberia eliminar a ninguna victima de la base de datos", directorio.darVictimas().size() , 1);

        }
        catch(Exception e)
        {
            //Aqu� debe llegar.
        }
    }
}
