package InterfazParoNacional;


import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ParoNacional.Victima;

/**
 * Clase que representa el panel donde se encuentra la lista de victimas
 */
public class PanelLista extends JPanel {
    /**
     * Atributo que representa la tabla que contiene a las victimas de la base datos
     */
    private JTable tabla;

    /**
     * Atributo que modela la relacion con la interfaz grafica
     */
    private InterfazParoNacional ventana;

    /**
     * Atributo que representa el scroll de la tabla de las victimas
     */
    private JScrollPane pane;

    /**
     * Atributo que representa el encabezada de la tabla
     */
    private String[] encabezado = {"ID", "Apellidos", "Edad"};

    /**
     * Este es el metodo que construye el panel de la lista segun las especificaciones
     *
     * @param pVentana
     */
    public PanelLista(InterfazParoNacional pVentana) {
        TitledBorder borde = BorderFactory.createTitledBorder("Lista Victimas");
        setBorder(borde);
        setLayout(new GridLayout(1, 2));

        ventana = pVentana;

        actualizar();

    }
    /**
     * Este metodo se encarga de crear la tabla con las victimas dentro y posteiormente agregarla al panel
     */
    public void actualizar()
    {
        ArrayList<Victima> vic = ventana.darVictimas();
        String[][] res = new String[vic.size()][3];

        for (int i = 0; i < vic.size(); i++)
        {
            res[i][0] = vic.get(i).darId();
            res[i][1] = vic.get(i).darApellidos();
            res[i][2] = vic.get(i).fechaNacimiento();
        }

        DefaultTableModel mod = new DefaultTableModel(res, encabezado);

        tabla = new JTable(mod);

        tabla.setEnabled(false);

        pane = new JScrollPane(tabla);
        pane.setBounds(40,40,400,200);

        add(pane);
    }
}
