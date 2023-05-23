package presentation.views;

import presentation.views.custom.BackGroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * sera la clase para construir la vista de deleteView de nuestro programa
 */
public class DeleteView extends JPanel {
    public static final String BTN_CHA = "BTN_DEL";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";

    private JComboBox<String> comboBox = new JComboBox<String>();

    private JButton deleteButton = new JButton("Delete Game");
    private JButton jbBack = new JButton("Back");
    private JButton jbSettings = new JButton("Settings");

    private CardLayout components;
    private MainView mainView;

    /**
     * este es nuestro constructor de delete
     */
    public DeleteView() {
        configureDeleteView();
        //chargeExistingGames(new String[]{"A", "B", "C"});
    }

    public void updateComboBoxList(ArrayList<String> games){
        comboBox.removeAllItems();
        for(String game: games){
            comboBox.addItem(game);
        }
    }

    /**
     * este es el metodo que genera la vista entera de deleteView
     */
    private void configureDeleteView() {
        BackGroundPanel fondo = new BackGroundPanel("files/images/background.jpg");
        fondo.setLayout(new BorderLayout());

        //this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

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
        JLabel jlTitle = new JLabel("Delete game");
        jlTitle.setForeground(Color.WHITE);
        jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Serif", Font.PLAIN, 40));
        titlePanel.setOpaque(false);
        titlePanel.add(jlTitle);
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
        jlCharge.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlCharge.setFont(new Font("Serif", Font.PLAIN, 20));

        infoPanel.add(jlCharge);


        //TextField de cargar partida
        comboBox.setMaximumSize(new Dimension(500, comboBox.getPreferredSize().height));
        infoPanel.add(Box.createHorizontalGlue()); // Add horizontal glue to align the text field to the center
        infoPanel.add(comboBox);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.setOpaque(false);
        auxPanel.add(infoPanel);
        fondo.add(auxPanel);

        //Panel de botones
        JPanel buttonPanel = new JPanel();
        deleteButton.setActionCommand(BTN_CHA);
        buttonPanel.add(deleteButton);
        buttonPanel.setOpaque(false);
        infoPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * funcion que sirve para asociar los botones con sus action listeners
     * @param actionListener actionListener para asociar el resultado de los botones, para controlar si se pulsan
     */
    public void deleteController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        jbSettings.addActionListener(actionListener);
        deleteButton.addActionListener(actionListener);
    }

    /**
     *
     * @param options
     */
    public void chargeExistingGames(String[] options){
        for(String option: options){
            comboBox.addItem(option);
        }

    }

    /**
     *
     * @return
     */
    public String optionSelected(){
        String selectedOption = comboBox.getSelectedItem().toString();
        return selectedOption;
    }

    /**
     *
     * @param action
     * @return
     */
    public int confirmPopUp(String action) {
        return JOptionPane.showConfirmDialog(null,action);
    }
}
