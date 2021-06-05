package ParoNacional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * Clase que representa un directorio para el manejo de base de datos de las victimas del conflicto social que ha dado a riz del paro nacional
 */
public class Directorio
{
    /**
     *Lista que representa la base de datos de victimas
     */
    ArrayList<Victima> victimas;

    /**
     * Este metodo es el que construye el directorio de acuerdo a un archivo csv donde est�  la informacion de las victimas
     */
    public Directorio(String pArchivo)
    {
        victimas = new ArrayList<Victima>();

        // Si existe un archivo con ese nombre entonces se carga la informacion de su contenido.
        if(new File(pArchivo).exists())
        {
            cargarDirectorio(pArchivo);
        }
    }

    /**
     * Este metodo regresa la lista de victimas
     * @return Lista de victimas
     */
    public ArrayList<Victima> darVictimas()
    {
        return victimas;
    }

    /**
     * Este metodo se encarga de leer el archivo donde se encuentra la informacion de las victimas (PERSISTENCIA)
     * @param pArchivo Ruta del archivo
     */
    public void cargarDirectorio(String pArchivo)
    {
        try
        {
            CsvReader lector = new CsvReader(pArchivo); //Obejeto de la libreria javacsv que lee archivos .csv

            while(lector.readRecord()) // Iterar� hasta que no encuentre mas lineas.
            {
                String nombres = lector.get(0);
                String apellidos = lector.get(1);
                String id = lector.get(2);
                String ciudadOrigen = lector.get(3);
                String hechos = lector.get(4);
                String fechaN = lector.get(5);

                Victima victimaArchivo = new Victima(nombres, apellidos, id, ciudadOrigen, hechos, fechaN); //Se crea la victima en el sistema segun el archivo .csv

                victimas.add(victimaArchivo); //Se agrega la victima a la base de datos (lista ArrayLisy) del sistema.
            }

            lector.close(); //Se cierra el archivo.
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo agrega una nueva victima en la base datos
     * @param nuevaVictima Nueva victima a agregar en la base de datos
     */
    public void agregarVictima(Victima nuevaVictima)
    {
        victimas.add(nuevaVictima);
    }

    /**
     * Este metodo busca una victima en la base de datos de acuerdo al nombre y apellido ingresado por el usuario
     * @param pNombre Nombre de la victima a buscar
     * @param pApellido Apellido de la victima a buscar
     * @return La victima que se esta buscando
     * @throws Exception Se lanza excepcion cuando se intenta buscar una victima que no existe en la base de datos
     */
    public Victima buscarVictima(String pNombre, String pApellido) throws Exception
    {
        Victima victima = null;

        for (int i = 0; i < victimas.size(); i++)
        {
            Victima buscada = victimas.get(i);

            if((buscada.darNombres().compareToIgnoreCase(pNombre) == 0) && buscada.darApellidos().compareToIgnoreCase(pApellido) == 0)
            {
                victima = buscada;
            }
        }

        if(victima == null)
        {
            throw new Exception("La victima con nombre: "+ pNombre +"\ny apellido: "+ pApellido+"\n\nNO se encuentra en la base de datos del sistema." );
        }

        return victima;
    }

    /**
     * Este metodo edita una victima dentro de la base de datos.
     * @param pNombreAnterior Nombre actual de la victima con el cual se puede encontrar en la base de datos
     * @param pApellidoAnterior Apellido actual de la victima con el cual de puede encontrar en la base datos
     * @param pNombreNuevo Nombre editado, si se ha de editar
     * @param pApellidoNuevo Apellido editado, si se ha de editar
     * @param pCiudadOrigenNueva Ciudad de origen editada, si se ha de editar
     * @param pHechoNuevo Hecho editado, si se ha de editar
     * @throws Exception Se lanza excepcion cuando la victima a editar no se encuentra dentro de la base de datos
     */
    public void editarVictima(String pNombreAnterior, String pApellidoAnterior, String pNombreNuevo, String pApellidoNuevo, String pCiudadOrigenNueva, String pHechoNuevo) throws Exception
    {
        Victima victima = buscarVictima(pNombreAnterior, pApellidoAnterior);

        for (int i = 0; i < victimas.size(); i++)
        {
            Victima buscada = victimas.get(i);
            String nombreBuscada = buscada.darNombres();
            String apellidoBuscada = buscada.darApellidos();

            if(nombreBuscada.compareToIgnoreCase(victima.darNombres()) == 0 && apellidoBuscada.compareToIgnoreCase(victima.darApellidos()) == 0)
            {
                (victimas.get(i)).editarNombres(pNombreNuevo);
                (victimas.get(i)).editarApellidos(pApellidoNuevo);
                (victimas.get(i)).editarCiudadOrigen(pCiudadOrigenNueva);
                (victimas.get(i)).editarHecho(pHechoNuevo);
            }
        }
    }

    /**
     * Este metodo elimina una victima que se encuentra en la base de datos
     * @param pNombre Nombre de la victima a eliminar
     * @param apellido Apellido de la victima a eliminar
     * @throws Exception Se lanza excepcion cuando la victima a eliminar no se encuentra dentro de la base de datos
     */
    public void eliminarVictima(String pNombre, String apellido) throws Exception
    {
        Victima victimaAEliminar = buscarVictima(pNombre, apellido);

        victimas.remove(victimaAEliminar);
    }

    /**
     * Este metodo se encarga de exportar toda la informacion de las victimas dentro de la base de datos a un archivo con extension .csv
     */
    public void exportarDirectorioCSV()
    {
        LocalDate fecha = LocalDate.now();
        Date resto = new Date();

        String anio = fecha.getYear()+"";
        String mes = fecha.getMonthValue()+"";
        String dia = fecha.getDayOfMonth()+"";
        String hora = resto.getHours()+"";
        String min = resto.getMinutes()+"";
        String seg = resto.getSeconds()+"";
        String s = "-";

        String persitencia = "Exportado/directorio-"+anio+s+mes+s+dia+s+hora+s+min+s+seg+".csv";

        if(new File(persitencia).exists())
        {
            File archivo = new File(persitencia);
            archivo.delete();
        }

        try
        {
            CsvWriter escritor = new CsvWriter(new FileWriter(persitencia,true),',');

            escritor.write("Nombre");
            escritor.write("Apellido");
            escritor.write("ID");
            escritor.write("Ciudad Origen");
            escritor.write("Hechos");
            escritor.write("Fecha");


            escritor.endRecord();

            for (int i = 0; i < victimas.size(); i++)
            {
                escritor.write(victimas.get(i).darNombres());
                escritor.write(victimas.get(i).darApellidos());
                escritor.write(victimas.get(i).darId());
                escritor.write(victimas.get(i).darCiudadOrigen());
                escritor.write(victimas.get(i).darHechos());
                escritor.write(String.valueOf(victimas.get(i).fechaNacimiento()));

                escritor.endRecord();
            }

            escritor.close();
        }
        catch(IOException e)
        {
            e.getMessage();
        }
    }

    public void fechaNacimiento(String fechaN){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse("15/08/2021", fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        System.out.printf("Tu edad es: %s años, %s meses y %s días",
                periodo.getYears(), periodo.getMonths(), periodo.getDays());
    }

    /**
     * Este metodo se encarga de crear un archivo .csv con toda la informacion de la base de datos para luego ser cargado
     * cuando el programa se ejecute nuevamente (PERSISTENCIA)
     */
    public void persisntenciaCSV()
    {
        String persitencia = "ArchivoPersistencia/Directorio.csv";

        if(new File(persitencia).exists())
        {
            File archivo = new File(persitencia);
            archivo.delete();
        }

        try
        {
            CsvWriter escritor = new CsvWriter(new FileWriter(persitencia,true),',');

            for (int i = 0; i < victimas.size(); i++)
            {
                escritor.write(victimas.get(i).darNombres());
                escritor.write(victimas.get(i).darApellidos());
                escritor.write(victimas.get(i).darId());
                escritor.write(victimas.get(i).darCiudadOrigen());
                escritor.write(victimas.get(i).darHechos());
                escritor.write(String.valueOf(victimas.get(i).fechaNacimiento()));


                escritor.endRecord();
            }

            escritor.close();
        }
        catch(IOException e)
        {
            e.getMessage();
        }
    }
}
