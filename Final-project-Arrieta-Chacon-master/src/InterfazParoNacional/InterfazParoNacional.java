package InterfazParoNacional;


import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ParoNacional.Directorio;
import ParoNacional.Victima;

/**
 * Clase que representa la interfaz grafica
 */
public class InterfazParoNacional   extends JFrame {
    /**
     * Constante que representa la ruta del archivo al cual se persiste
     */
    public static final String RUTA_PERSISTENCIA = "ArchivoPersistencia/Directorio.csv";

    /**
     * Atributo que modela la relacion con el panel de botones
     */
    private PanelBotones panelBotones;

    /**
     * Atributo que modela la relacion con el panel de la lista de victimas
     */
    private PanelLista panelLista;

    /**
     * Atributo que modela la relacion con el panel de informacion de la victima buscada
     */
    private PanelInfo panelInfo;

    /**
     * Atributo que modela la relacion de la interfaz grafica y la parte logica del programa
     */
    private Directorio directorio;

    /**
     * Este metodo se encarga de contruir la interfaz grafica
     */
    public InterfazParoNacional() {
        setLayout(new BorderLayout());
        setTitle("PARO NACIONAL 2021");
        setSize(800, 500);
        setResizable(false);

        directorio = new Directorio(RUTA_PERSISTENCIA);

        panelBotones = new PanelBotones(this);
        panelLista = new PanelLista(this);
        panelInfo = new PanelInfo();

        JPanel aux = new JPanel();
        aux.setLayout(new GridLayout(1, 2));

        aux.add(panelLista);
        aux.add(panelInfo);

        add(panelBotones, BorderLayout.SOUTH);
        add(aux, BorderLayout.CENTER);

        cerrar();
    }
    /**
     * Este metodo regresa la lista de victimas(Base de datos)
     * @return Lista de victimas
     */
    public ArrayList<Victima> darVictimas()
    {
        return directorio.darVictimas();
    }

    /**
     * Este metodo se encarga de darle incio a la logica para agregar una nueva victima
     * @param nuevaVictima Victima nueva para ser agregada en la base de datos
     */
    public void agregarVictima(Victima nuevaVictima)
    {
        directorio.agregarVictima(nuevaVictima);
    }

    /**
     * Este metodo se encarga de darle inicio a la logica para buscar una victima
     * @param pNombre Nombre de la victima que el usuario desea buscar
     * @param pApellido Apellido de la victima que el usuario desea buscar
     */
    public void buscarVictima(String pNombre, String pApellido)
    {
        try
        {
            Victima encontrada = directorio.buscarVictima(pNombre, pApellido);
            panelInfo.editar(encontrada);
            JOptionPane.showMessageDialog(null, "Puede visualizar la informaci?n mas detallada en el panel de INFORMACI?N", "EXITOSO", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Este metodo se encarga de buscar una victima
     * @param pNombre Nombre de la victima buscada
     * @param pApellido Apellido de la victima
     * @return Victima buscada
     * @throws Exception Se lanza excepcion cuando la victima que el usuario busca no esta dentro de la base de datos
     */
    public Victima buscada(String pNombre, String pApellido) throws Exception
    {
        return directorio.buscarVictima(pNombre, pApellido);
    }

    /**
     * Este metodo se encarga de darle inicio a la logica para qwue edite una victima
     * @param pNombreAnterior Nombre actual de la victima dentro de la base de datos
     * @param pApellidoAnterior Apellido actual de la victima dentro de la base de datos
     * @param pNombreNuevo Nombre editado
     * @param pApellidoNuevo Apellido editado
     * @param pCiudadOrigenNueva Ciudad de origen editada
     * @param pHechoNuevo Hecho editado
     */
    public void editarVictima(String pNombreAnterior, String pApellidoAnterior, String pNombreNuevo, String pApellidoNuevo, String pCiudadOrigenNueva, String pHechoNuevo)
    {
        try
        {
            directorio.editarVictima(pNombreAnterior, pApellidoAnterior, pNombreNuevo, pApellidoNuevo, pCiudadOrigenNueva, pHechoNuevo);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Este metodo se encarga de darle incio a la logica para eliminar una victima
     * @param nombre Nombre de la victima a eliminar
     * @param apellido Apellido de la victima a eliminar
     */
    public void eliminarVictima(String nombre, String apellido)
    {
        try
        {
            directorio.eliminarVictima(nombre, apellido);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Este metodo se encarga de darle inicio a la logica para que exporte un archivo .csv con toda la informacion de la base de datos
     */
    public void exportarArchivoCSV()
    {
        directorio.exportarDirectorioCSV();
    }

    /**
     * Este metodo se encarga de crear un codigo se forma interna cuando se agrega una nueva victima
     * @return
     */
    public String generarID()
    {
        int id = 1000;

        if(darVictimas().size() != 0)
        {
            id = Integer.parseInt((darVictimas().get(darVictimas().size() - 1)).darId())+1;
        }

        return id+"";
    }

    /**
     * Este metodo se encarga de escuchar cuando el usuario cierra la ventana de la interfaz grafica, para luego darle inicio a la logica
     * para que exporte un archivo de extenion .csv para luego ser persistido con toda la informacion de la base de datos
     */
    public void cerrar()
    {
        try
        {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e)
                {
                    directorio.persisntenciaCSV();
                    System.exit(0);
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void fechaNacimiento(String fecha ){

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse("15/08/1993", fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        System.out.printf("Tu edad es: %s años, %s meses y %s días",
                periodo.getYears(), periodo.getMonths(), periodo.getDays());


    }

    /**
     * metodo que da inicio a la aplicacion
     * @param args Argumentos
     */
    public static void main(String[]  args)
    {
        InterfazParoNacional interfaz = new InterfazParoNacional();
        interfaz.setVisible(true);
    }
}