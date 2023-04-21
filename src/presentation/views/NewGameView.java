package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;




public class NewGameView extends JPanel {


    public static final String BTN_CHA = "BTN_CHA";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";

    private JTextField nameField = new JTextField();


    private JButton chargeButton = new JButton("Charge");
    private JButton jbBack = new JButton();
    private JButton jbSettings = new JButton();


    private CardLayout components;
    private MainView mainView;

    public NewGameView(){
        configureNGView();
    }

    private void configureNGView(){

        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());

        JPanel jpActions = new JPanel();
        jbBack.setActionCommand(BTN_BACK);
        jbSettings.setActionCommand(BTN_STI);
        jpActions.add(jbBack);
        jpActions.add(jbSettings);
        this.add(jpActions, BorderLayout.NORTH);

        JPanel titlePanel = new JPanel();
        JLabel jlTitle = new JLabel("Charge game");
        jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Serif", Font.PLAIN, 40));
        titlePanel.add(jlTitle);
        //this.add(titlePanel, BorderLayout.NORTH);


        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        this.add(voidPanel, BorderLayout.EAST);
        this.add(voidPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //Label de cargar partida
        JLabel jlCharge = new JLabel("Game name");
        jlCharge.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlCharge.setFont(new Font("Serif", Font.PLAIN, 20));
        //TODO: Cambiar el color de la vista
        infoPanel.add(jlCharge);



        //TextField de cargar partida
        nameField.setMaximumSize(new Dimension(500, nameField.getPreferredSize().height));
        infoPanel.add(Box.createHorizontalGlue()); // Add horizontal glue to align the text field to the center
        infoPanel.add(nameField);
        infoPanel.add(Box.createVerticalStrut(10));
        this.add(infoPanel);
        //Panel de Jlabels
        //jlabel 1
        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("Color");
        JTextField textField = new JTextField(20);
        labelPanel.add(label);
        labelPanel.add(textField);
        infoPanel.add(labelPanel);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical



//jlabel 2
        JPanel labelPanel2 = new JPanel();
        JLabel label2 = new JLabel("Impostors");
        JSpinner spinner2 = new JSpinner();
        JSpinner.DefaultEditor editor2 = (JSpinner.DefaultEditor) spinner2.getEditor();
        editor2.getTextField().setColumns(20); // Establecer 20 caracteres de ancho
        labelPanel2.add(label2);
        labelPanel2.add(spinner2);
        infoPanel.add(labelPanel2);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical

//jlabel 3
        JPanel labelPanel3 = new JPanel();
        JLabel label3 = new JLabel("Players");
        JSpinner spinner3 = new JSpinner();
        JSpinner.DefaultEditor editor3 = (JSpinner.DefaultEditor) spinner3.getEditor();
        editor3.getTextField().setColumns(20); // Establecer 20 caracteres de ancho
        labelPanel3.add(label3);
        labelPanel3.add(spinner3);
        infoPanel.add(labelPanel3);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical

//jlabel 4
        JPanel labelPanel4 = new JPanel();
        JLabel label4 = new JLabel("Map");
        JSpinner spinner4 = new JSpinner();
        JSpinner.DefaultEditor editor4 = (JSpinner.DefaultEditor) spinner4.getEditor();
        editor4.getTextField().setColumns(20); // Establecer 20 caracteres de ancho
        labelPanel4.add(label4);
        labelPanel4.add(spinner4);
        infoPanel.add(labelPanel4);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical


        //Panel de botones
        JPanel buttonPanel = new JPanel();
        chargeButton.setActionCommand(BTN_CHA);
        buttonPanel.add(chargeButton);

        infoPanel.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void NGController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        chargeButton.addActionListener(actionListener);
        jbSettings.addActionListener(actionListener);
    }


}
