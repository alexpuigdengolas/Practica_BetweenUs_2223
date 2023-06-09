package presentation.views;

import presentation.views.custom.BackGroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * sera la clase para construir la vista de new game de nuestro programa
 */
public class NewGameView extends JPanel {


    public static final String BTN_CHA = "BTN_CHA";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";

    public static final String BTN_MAP= "BTN_MAP";
    private String fileName = "Select File";

    private final JTextField nameField = new JTextField();


    private final JButton chargeButton = new JButton("Charge");
    private final JButton jbBack = new JButton("Back");
    private final JButton jbSettings = new JButton("Settings");
    private final JSpinner spinnerPly = new JSpinner(new SpinnerNumberModel(4,4,10,1));

    JSpinner spinnerImp = new JSpinner(new SpinnerNumberModel(1,1,3,1));

    private final String[] colors = {"RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"};
    private final JComboBox<String> jBCColors = new JComboBox<>(colors);



   private final JButton  jbmaps = new JButton(fileName);

    /**
     * constructor de newgame
     */
    public NewGameView(){
        configureNGView();
    }

    /**
     * este es el metodo que genera la vista entera de newGame
     */
    private void configureNGView(){

        BackGroundPanel fondo = new BackGroundPanel("files/images/background.jpg");
        fondo.setLayout(new BorderLayout());

        this.setLayout(new BorderLayout());
        this.add(fondo);

        JPanel auxPanel = new JPanel();
        auxPanel.setLayout(new BorderLayout());
        auxPanel.setOpaque(false);

        JPanel jpActions = new JPanel();
        jpActions.setLayout(new BoxLayout(jpActions, BoxLayout.X_AXIS));

        jbBack.setActionCommand(BTN_BACK);
        jpActions.add(jbBack);

        // Espai buit per emputjar el segon botó a la dreta
        jpActions.add(Box.createHorizontalGlue());

        jbSettings.setActionCommand(BTN_STI);
        jpActions.add(jbSettings);
        jpActions.setOpaque(false);
        fondo.add(jpActions, BorderLayout.NORTH);


        JPanel titlePanel = new JPanel();
        JLabel jlTitle = new JLabel("New Game");
        jlTitle.setForeground(Color.WHITE);
        jlTitle.setHorizontalAlignment(0);
        jlTitle.setFont(new Font("Serif", Font.PLAIN, 40));
        titlePanel.add(jlTitle);
        titlePanel.setOpaque(false);
        auxPanel.add(titlePanel, BorderLayout.NORTH);


        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        voidPanel.setOpaque(false);
        fondo.add(voidPanel, BorderLayout.EAST);
        fondo.add(voidPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //Label de cargar partida
        JLabel jlCharge = new JLabel("Game name");
        jlCharge.setForeground(Color.WHITE);
        jlCharge.setHorizontalAlignment(0);
        jlCharge.setFont(new Font("Serif", Font.PLAIN, 20));
        infoPanel.add(jlCharge);



        //TextField de cargar partida
        nameField.setMaximumSize(new Dimension(500, nameField.getPreferredSize().height));
        infoPanel.add(Box.createHorizontalGlue()); // Add horizontal glue to align the text field to the center
        infoPanel.add(nameField);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.setOpaque(false);
        auxPanel.add(infoPanel);
        fondo.add(auxPanel);
        //Panel de Jlabels
        //jlabel 1
        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("Color");
        label.setForeground(Color.WHITE);
        labelPanel.add(label);
        labelPanel.add(jBCColors);
        infoPanel.add(labelPanel);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical
        labelPanel.setOpaque(false);



//jlabel 2
        JPanel labelPanel2 = new JPanel();
        JLabel label2 = new JLabel("Impostors");
        label2.setForeground(Color.WHITE);
        JSpinner.DefaultEditor editorImp = (JSpinner.DefaultEditor) spinnerImp.getEditor();
        editorImp.getTextField().setColumns(20); // Establecer 20 caracteres de ancho
        labelPanel2.add(label2);
        labelPanel2.add(spinnerImp);
        infoPanel.add(labelPanel2);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical
        labelPanel2.setOpaque(false);

//jlabel 3
        JPanel labelPanel3 = new JPanel();
        JLabel label3 = new JLabel("Players");
        label3.setForeground(Color.WHITE);
        JSpinner.DefaultEditor editorPly = (JSpinner.DefaultEditor) spinnerPly.getEditor();
        editorPly.getTextField().setColumns(20); // Establecer 20 caracteres de ancho
        labelPanel3.add(label3);
        labelPanel3.add(spinnerPly);
        infoPanel.add(labelPanel3);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical
        labelPanel3.setOpaque(false);

//jlabel 4
        JPanel labelPanel4 = new JPanel();
        JLabel label4 = new JLabel("Map");
        label4.setForeground(Color.WHITE);
        jbmaps.setActionCommand(BTN_MAP);
        jbmaps.setBackground(Color.BLACK);
        jbmaps.setBounds(570,320,300,50);
        labelPanel4.add(label4);
        labelPanel4.add(jbmaps);
        infoPanel.add(labelPanel4);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical
        labelPanel4.setOpaque(false);


        //Panel de botones
        JPanel buttonPanel = new JPanel();
        chargeButton.setActionCommand(BTN_CHA);
        buttonPanel.add(chargeButton);
        buttonPanel.setOpaque(false);
        infoPanel.add(buttonPanel, BorderLayout.SOUTH);

    }

    /**
     * funcion que sirve para asociar los botones con sus action listeners
     * @param actionListener actionListener para asociar el resultado de los botones, para controlar si se pulsan
     */
    public void NGController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        chargeButton.addActionListener(actionListener);
        jbSettings.addActionListener(actionListener);
        jbmaps.addActionListener(actionListener);
    }

    /**
     * @return jBCColors Devuelve el color seleccionado como una cadena.
     */
    public String getColor(){
        return jBCColors.getSelectedItem().toString();
    }

    /**
     * @return spinnerPly Devuelve el número de jugadores seleccionado como un entero.
     */
    public int getPlayers(){
        return (int) spinnerPly.getValue();
    }

    /**
     * @return  spinnerImp Devuelve el número de impostores seleccionado como un entero.
     */
    public  int getImp(){
        return (int) spinnerImp.getValue();
    }

    /**
     * @return nameField Devuelve el nombre del mapa ingresado como una cadena.
     */
    public String getNameMap(){
        return nameField.getText();
    }

    /**
     * @return jbmaps Devuelve el contenido del campo de texto jbmaps como una cadena.
     */
    public String getMap(){
        return jbmaps.getText();
    }

    /**
     * Establece el nombre del mapa y actualiza el texto del botón jbmaps.
     * @param mapName
     */
    public void setMapName(String mapName){
        this.fileName = mapName;

        jbmaps.setText(fileName);
    }

    /**
     * Muestra un mensaje de error con el texto proporcionado.
     * @param Error
     */
    public void printNewGameErrors(String Error) {
        JOptionPane.showMessageDialog(null, Error, "Error Registre", JOptionPane.ERROR_MESSAGE);
    }
}
