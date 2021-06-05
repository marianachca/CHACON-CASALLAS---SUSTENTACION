package PruebaUnitaria;
import ParoNacional.Victima;
import junit.framework.TestCase;

/**
 * Clase usada para verificar los metodos de la calse Victima
 */
public class PruebaVictima extends TestCase
{
    /**
     * Victima de prueba
     */
    private Victima victima;

    /**
     * Escenario que crea el objeto victima de prueba
     */
    public void escenario()
    {
        victima = new Victima("lola", "paredes", "1000", "barranquilla", "Fue hallada sin vida a la orilla del rio magdalena", "40");
    }

    /**
     * Prueba que verifica la edicion del nombre de la victima
     */
    public void testEditarNombre()
    {
        escenario();
        victima.editarNombres("luna");
        assertEquals( "No se edito el nombre de forma correcta", victima.darNombres(), "luna");
    }

    /**
     * Prueba que verifica la edicion del apellido de la victima
     */
    public void testEditarApellido()
    {
        escenario();
        victima.editarApellidos("cespedes");
        assertEquals( "No se edito el apellido de forma correcta", victima.darApellidos(), "cespedes");
    }

    /**
     * Prueba que verifica la edicion de la ciudad de origen de la victima
     */
    public void testEditarCiudadOrigen()
    {
        escenario();
        victima.editarCiudadOrigen("manizales");
        assertEquals( "No se edito la ciudad de origen de forma correcta", victima.darCiudadOrigen(), "manizales");
    }

    /**
     * Prueba que verifica la edicion del hecho de la victima
     */
    public void testEditarHecho()
    {
        escenario();
        victima.editarHecho("Fue hallada sin vida a la orilla del ca�o la ahuyama");
        assertEquals( "No se edito el hecho(rio magdalena a ca�o la ahuyama) de forma correcta", victima.darHechos(), "Fue hallada sin vida a la orilla del ca�o la ahuyama");
    }

}
