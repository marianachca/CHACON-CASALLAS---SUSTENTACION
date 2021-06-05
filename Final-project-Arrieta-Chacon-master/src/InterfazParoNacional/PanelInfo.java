package InterfazParoNacional;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ParoNacional.Victima;

/**
 * Clase que representa el panel que muestra la informacion de una victima buscada
 *
 */
public class PanelInfo extends JPanel
{
    /**
     * Atributo que representa la etiqueta de la imagen
     */
    private JLabel lblImagen;

    /**
     * Atributo que representa el campo del nombre de la victima
     */
    private JTextField txtNombre;

    /**
     * Atributo que representa el campo del apellido de la victima
     */
    private JTextField txtApellido;

    /**
     * Atributo que representa el campo de la ciudad de origen de la victima
     */
    private JTextField txtCiudad;

    /**
     * Atributo que representa el campo de los hechos que protagoniz� la victima
     */
    private JTextArea lblHechos;

    /**
     * Este metodo se encarga de contruir el panel de informacion dentro de la interfaz
     */
    public PanelInfo() {
        TitledBorder borde = BorderFactory.createTitledBorder("Informaci�n");
        setBorder(borde);

        setLayout(new GridLayout(2, 1));

        lblImagen = new JLabel();
        ImageIcon icono = new ImageIcon(new ImageIcon("pic/siluetaV.jpg").getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT));
        lblImagen.setIcon(icono);
        lblImagen.setHorizontalAlignment(JLabel.CENTER);

        txtNombre = new JTextField();
        txtNombre.setEditable(false);

        txtApellido = new JTextField();
        txtApellido.setEditable(false);

        txtCiudad = new JTextField();
        txtCiudad.setEditable(false);

        lblHechos = new JTextArea();

        lblHechos.setLineWrap(true);
        lblHechos.setWrapStyleWord(true);
        lblHechos.setOpaque(false);
        lblHechos.setEditable(false);

        JPanel aux1Hechos = new JPanel();
        aux1Hechos.setLayout(new BorderLayout());

        aux1Hechos.add(lblHechos);
        TitledBorder bordeHecho = BorderFactory.createTitledBorder("Descripci�n del Hecho");
        aux1Hechos.setBorder(bordeHecho);


        JPanel aux2Info = new JPanel();
        aux2Info.setLayout(new GridLayout(1, 2));

        JPanel aux3InfoVictima = new JPanel();
        aux3InfoVictima.setLayout(new GridLayout(8, 1, 5, 5));

        aux3InfoVictima.add(new JLabel());

        aux3InfoVictima.add(new JLabel("Nombres:"));
        aux3InfoVictima.add(txtNombre);

        aux3InfoVictima.add(new JLabel("Apellidos:"));
        aux3InfoVictima.add(txtApellido);

        aux3InfoVictima.add(new JLabel("Ciudad de Origen:"));
        aux3InfoVictima.add(txtCiudad);

        aux3InfoVictima.add(new JLabel());

        aux2Info.add(lblImagen);
        aux2Info.add(aux3InfoVictima);

        add(aux2Info);
        add(aux1Hechos);
    }

    /**
     * Este metodo se encarga de editar los campos segun la informacion de la victima que el usuario busca
     * @param pVictima
     */
    public void editar(Victima pVictima)
    {
        txtNombre.setText(pVictima.darNombres());
        txtApellido.setText(pVictima.darApellidos());
        txtCiudad.setText(pVictima.darCiudadOrigen());
        lblHechos.setText(pVictima.darHechos());
    }
}
