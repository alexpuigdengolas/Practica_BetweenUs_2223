package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChargeView extends JPanel{
    public static final String BTN_CHA = "BTN_CHA";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";
    //prova

    private JComboBox<String> comboBox = new JComboBox<String>();


    private JButton chargeButton = new JButton("Charge");
    private JButton jbBack = new JButton();
    private JButton jbSettings = new JButton();


    private CardLayout components;
    private MainView mainView;

    public ChargeView() {
        configureChargeView();
        chargeExistingGames(new String[]{"A", "B", "C"});
    }

    private void configureChargeView() {
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
        comboBox.setMaximumSize(new Dimension(500, comboBox.getPreferredSize().height));
        infoPanel.add(Box.createHorizontalGlue()); // Add horizontal glue to align the text field to the center
        infoPanel.add(comboBox);
        infoPanel.add(Box.createVerticalStrut(10));
        this.add(infoPanel);

        //Panel de botones
        JPanel buttonPanel = new JPanel();
        chargeButton.setActionCommand(BTN_CHA);
        buttonPanel.add(chargeButton);

        infoPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void chargeController(ActionListener listener) {
        chargeButton.addActionListener(listener);
        jbBack.addActionListener(listener);
        jbSettings.addActionListener(listener);
    }

    public void chargeExistingGames(String[] options){
        for(String option: options){
            comboBox.addItem(option);
        }

        //TODO: Acceso real a la base de datos
        comboBox.addItem("Option 1");
        comboBox.addItem("Option 2");
        comboBox.addItem("Option 3");
    }

    public String optionSelected(){
        return (String) comboBox.getSelectedItem();
    }

    /**
     * Setter para asignar la main view
     *
     * @param mainView vista principal
     */
    public void setMainView(MainView mainView){
        this.mainView = mainView;
    }

    /**
     * Setter para asignar los componentes del card layout
     *
     * @param viewComponents card layout
     */
    public void setComponents(CardLayout viewComponents) {
        this.components = viewComponents;
    }

}
