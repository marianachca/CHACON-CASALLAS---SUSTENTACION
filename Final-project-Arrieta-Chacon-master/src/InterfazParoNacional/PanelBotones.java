package InterfazParoNacional;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ParoNacional.Victima;

public class PanelBotones extends JPanel   implements ActionListener
{
    /**
     * Constante que representa el comando de agregar
     */
    public static final String AGREGAR = "Agregar";

    /**
     * Constante que representa el comando de buscar
     */
    public static final String BUSCAR = "Buscar";

    /**
     * Constante que representa el comando de editar
     */
    public static final String EDITAR = "Editar";

    /**
     * Constante que representa el comando de eliminar
     */
    public static final String ELIMINAR = "Eliminar";

    /**
     * Constante que representa el comando de exportar
     */
    public static final String EXPORTAR = "Exportar";

    /**
     * Atributo que modela la relacion del panel con la interfaz grafica
     */
    private InterfazParoNacional ventana;

    /**
     * Atributo que representa el boton de agregar
     */
    private JButton butAgregar;

    /**
     * Atributo que representa el boton de buscar
     */
    private JButton butBuscar;

    /**
     * Atributo que representa el boton de editar
     */
    private JButton butEditar;

    /**
     * Atributo que representa el boton de eliminar
     */
    private JButton butEliminar;

    /**
     * Atributo que representa el boton de exportar
     */
    private JButton butExportar;

    /**
     * Este metodo se encarga de crear en la panel de botones
     * @param pVentana Interfaz grafica madre
     */
    public PanelBotones(InterfazParoNacional pVentana) {
        ventana = pVentana;

        setLayout(new GridLayout(1, 5));
        setSize(100, 250);
        TitledBorder borde = BorderFactory.createTitledBorder("Opciones");
        setBorder(borde);

        //Creaci�n de los botones
        butAgregar = new JButton(AGREGAR);
        butAgregar.setActionCommand(AGREGAR);
        butAgregar.addActionListener(this);

        butBuscar = new JButton(BUSCAR);
        butBuscar.setActionCommand(BUSCAR);
        butBuscar.addActionListener(this);

        butEditar = new JButton(EDITAR);
        butEditar.setActionCommand(EDITAR);
        butEditar.addActionListener(this);

        butEliminar = new JButton(ELIMINAR);
        butEliminar.setActionCommand(ELIMINAR);
        butEliminar.addActionListener(this);

        butExportar = new JButton(EXPORTAR);
        butExportar.setActionCommand(EXPORTAR);
        butExportar.addActionListener(this);

        add(butAgregar);
        add(butBuscar);
        add(butEditar);
        add(butEliminar);
        add(butExportar);
    }
    /**
     * Este metodo se encarga de escuhar los botones oprimidos por el usuario
     */
    public void actionPerformed(ActionEvent evento)
    {
        String comando = evento.getActionCommand();
        String space = "                                          ";
        if(comando.equals(AGREGAR))
        {
            String apellidosU = JOptionPane.showInputDialog(null, "Ingrese APELLIDOS de la victima:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
            String nombresU = JOptionPane.showInputDialog(null, "Ingrese NOMBRES de la victima:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
            String ciudadOrigenU = JOptionPane.showInputDialog(null, "Ingrese la CIUDAD DE ORIGEN de la victima:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
            String hechosU = JOptionPane.showInputDialog(null, "Describa lo OCURRIDO con la victima:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
            String fechaU = JOptionPane.showInputDialog(null, "Ingrese fecha de nacimiento:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);

            if(!"".equals(nombresU) && !"".equals(apellidosU) && !"".equals(ciudadOrigenU) && !"".equals(hechosU))
            {
                if(nombresU != null && apellidosU != null && ciudadOrigenU != null && hechosU != null)
                {
                    String idgenerado = ventana.generarID();
                    Victima victimaAgregar = new Victima(nombresU.toUpperCase().trim(), apellidosU.toUpperCase().trim(), idgenerado.toUpperCase().trim(), ciudadOrigenU.toUpperCase().trim(), hechosU.trim(), fechaU.toUpperCase().trim());
                    ventana.agregarVictima(victimaAgregar);

                    JOptionPane.showMessageDialog(null, "LA VICTIMA SE HA INGRESADO CORRECTAMENTE CON CODIGO: \n\n"+space+"***"+idgenerado+"***\n\n", "EXITOSO", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {

                JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(comando.equals(BUSCAR))
        {
            String apellidoU = JOptionPane.showInputDialog(null, "Ingrese APELLIDOS COMPLETO de la victima:", "BUSCAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
            String nombreU = JOptionPane.showInputDialog(null, "Ingrese NOMBRES de la victima:", "BUSCAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);

            if(nombreU != null && apellidoU != null)
            {
                if(nombreU != "" && apellidoU != "")
                {
                    ventana.buscarVictima(nombreU.trim(), apellidoU.trim());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(comando.equals(EDITAR))
        {
            String apellidoAntes = JOptionPane.showInputDialog(null, "Ingrese APELLIDOS COMPLETO de la victima:", "BUSCAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
            String nombreAntes = JOptionPane.showInputDialog(null, "Ingrese NOMBRES de la victima:", "BUSCAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);

            if(nombreAntes != null && apellidoAntes != null)
            {
                if(nombreAntes != "" && apellidoAntes != "")
                {
                    try
                    {
                        if(ventana.buscada(nombreAntes, apellidoAntes) != null)
                        {
                            JOptionPane.showMessageDialog(null, "La victima SI existe en la base de datos\n\nPor favor ingrese los datos a editar de la victima a continuac�n", "VICTIMA REGISTRADA", JOptionPane.INFORMATION_MESSAGE);

                            String apellidosDespues = JOptionPane.showInputDialog(null, "Ingrese APELLIDOS de la victima:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
                            String nombresDespues = JOptionPane.showInputDialog(null, "Ingrese NOMBRES de la victima:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
                            String ciudadOrigenDespues = JOptionPane.showInputDialog(null, "Ingrese la CIUDAD DE ORIGEN de la victima:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
                            String hechosDespues = JOptionPane.showInputDialog(null, "Describa lo OCURRIDO con la victima:", "AGREGAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);

                            if(!"".equals(nombresDespues) && !"".equals(apellidosDespues) && !"".equals(ciudadOrigenDespues) && !"".equals(hechosDespues))
                            {
                                if(nombresDespues != null && apellidosDespues != null && ciudadOrigenDespues != null && hechosDespues != null)
                                {
                                    ventana.editarVictima(nombreAntes.trim(), apellidoAntes.trim(), nombresDespues.toUpperCase().trim(), apellidosDespues.toUpperCase().trim(), ciudadOrigenDespues.toUpperCase().trim(), hechosDespues.trim());

                                    JOptionPane.showMessageDialog(null, "LA VICTIMA SE HA *EDITADO* CORRECTAMENTE", "EXITOSO", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            else
                            {

                                JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(comando.equals(ELIMINAR))
        {
            String apellidoAntes = JOptionPane.showInputDialog(null, "Ingrese APELLIDOS COMPLETO de la victima:", "BUSCAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);
            String nombreAntes = JOptionPane.showInputDialog(null, "Ingrese NOMBRES de la victima:", "BUSCAR VICTIMA", JOptionPane.INFORMATION_MESSAGE);

            if(nombreAntes != null && apellidoAntes != null)
            {
                if(nombreAntes != "" && apellidoAntes != "")
                {
                    ventana.eliminarVictima(nombreAntes, apellidoAntes);
                    JOptionPane.showMessageDialog(null, "La victima de ha *ELIMINADO* exitosamente ", "EXITOSO", JOptionPane.INFORMATION_MESSAGE);

                    for (int i = 0; i < ventana.darVictimas().size(); i++)
                    {
                        System.out.println(ventana.darVictimas().get(i).darId() + " ** " + ventana.darVictimas().get(i).darApellidos()+ " " + ventana.darVictimas().size()) ;

                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS ESPACIOS CORRECTAMENTE.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(comando.equals(EXPORTAR))
        {
            ventana.exportarArchivoCSV();
            JOptionPane.showMessageDialog(null, "El archivo .csv lo puede encontrar en la carpeta de *EXPORTADO*", "EXITOSO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
