package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;




public class NewGameView extends JPanel {


    public static final String BTN_CHA = "BTN_CHA";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String SPN_PLY = "SPN_PLY";
    public static final String BTN_STI = "BTN_STI";

    public static final String BTN_MAP= "BTN_MAP";
    private String fileName = "Select File";

    private JTextField nameField = new JTextField();


    private JButton chargeButton = new JButton("Charge");
    private JButton jbBack = new JButton();
    private JButton jbSettings = new JButton();
    private JSpinner spinnerPly = new JSpinner(new SpinnerNumberModel(4,4,10,1));

    JSpinner spinnerImp = new JSpinner(new SpinnerNumberModel(1,1,3,1));

    private String colors []= {"Vermell","Blau","Verd","Rosa","Tronja", "Groc","Negre","Blanc","Lila","Marró","Cian","Lima"};
    private JComboBox<String> jBCColors = new JComboBox<>(colors);



   private JButton  jbmaps = new JButton(fileName);




    private CardLayout components;
    private MainView mainView;

    public NewGameView(){
        configureNGView();
    }

    private void configureNGView(){

        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());

        JPanel jpActions = new JPanel();
        jpActions.setLayout(new BoxLayout(jpActions, BoxLayout.X_AXIS));

        jbBack.setActionCommand(BTN_BACK);
        jpActions.add(jbBack);

        // Espai buit per emputjar el segon botó a la dreta
        jpActions.add(Box.createHorizontalGlue());

        jbSettings.setActionCommand(BTN_STI);
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
        labelPanel.add(label);
        labelPanel.add(jBCColors);
        infoPanel.add(labelPanel);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical



//jlabel 2
        JPanel labelPanel2 = new JPanel();
        JLabel label2 = new JLabel("Impostors");
        JSpinner.DefaultEditor editorImp = (JSpinner.DefaultEditor) spinnerImp.getEditor();
        editorImp.getTextField().setColumns(20); // Establecer 20 caracteres de ancho
        labelPanel2.add(label2);
        labelPanel2.add(spinnerImp);
        infoPanel.add(labelPanel2);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical

//jlabel 3
        JPanel labelPanel3 = new JPanel();
        JLabel label3 = new JLabel("Players");
        JSpinner.DefaultEditor editorPly = (JSpinner.DefaultEditor) spinnerPly.getEditor();
        editorPly.getTextField().setColumns(20); // Establecer 20 caracteres de ancho
        labelPanel3.add(label3);
        labelPanel3.add(spinnerPly);
        infoPanel.add(labelPanel3);
        infoPanel.add(Box.createVerticalStrut(10)); // Agregar espacio en blanco vertical

//jlabel 4
        JPanel labelPanel4 = new JPanel();
        JLabel label4 = new JLabel("Map");
        jbmaps.setActionCommand(BTN_MAP);
        jbmaps.setBackground(Color.BLACK);
        jbmaps.setForeground(Color.WHITE);
        jbmaps.setBounds(570,320,300,50);
        labelPanel4.add(label4);
        labelPanel4.add(jbmaps);
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
        jbmaps.addActionListener(actionListener);
    }

    public String getColor(){
        return jBCColors.getSelectedItem().toString();
    }
    public int getPlayers(){
        return (int) spinnerPly.getValue();
    }

    public  int getImp(){
        return (int) spinnerImp.getValue();
    }


    public String getNameMap(){
        return nameField.getText();
    }

    public String getMap(){
        System.out.println(jbmaps.getText());
        return jbmaps.getText();
    }

    public void setMapName(String mapName){
        this.fileName = mapName;

        jbmaps.setText(fileName);
    }

    public void printNewGameErrors(String Error) {
        JOptionPane.showMessageDialog(null, Error, "Error Registre", JOptionPane.ERROR_MESSAGE);

    }


}
