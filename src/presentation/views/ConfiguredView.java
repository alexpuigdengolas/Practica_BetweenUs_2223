package presentation.views;

import presentation.views.custom.BackGroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * sera la clase para construir la vista de la configuracion de nuestro programa
 */
public class ConfiguredView extends JPanel {
    public static final String BTN_CHA = "BTN_CON";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";


    private final JComboBox<String> comboBox = new JComboBox<String>();


    private final JButton configuredButton = new JButton("Create Game");
    private final JButton jbBack = new JButton("Back");
    private final JButton jbSettings = new JButton("Settings");

    /**
     * es el constructor de configure
     */
    public ConfiguredView() {
        configureConfiguredView();
    }

    /**
     * funcion para actualitzar los valores de la comboBox
     * @param games es la lista de los juegos actualitzados
     */
    public void updateComboBoxList(ArrayList<String> games){
        comboBox.removeAllItems();
        for(String game: games){
            comboBox.addItem(game);
        }
    }

    /**
     * este es el metodo que genera la vista entera de configureView
     */
    private void configureConfiguredView() {

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
        JLabel jlTitle = new JLabel("Configured game");
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

        //ara esta be
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //Label de cargar partida
        JLabel jlCharge = new JLabel("Game name");
        jlCharge.setForeground(Color.WHITE);
        jlCharge.setHorizontalAlignment(0);
        jlCharge.setFont(new Font("Serif", Font.PLAIN, 20));
        infoPanel.add(jlCharge);


        //TextField de cargar partida
        comboBox.setMaximumSize(new Dimension(500, comboBox.getPreferredSize().height));
        infoPanel.add(Box.createHorizontalGlue()); // Add horizontal glue to align the text field to the center
        infoPanel.add(comboBox);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.setOpaque(false);
        auxPanel.add(infoPanel, BorderLayout.CENTER);

        fondo.add(auxPanel);

        //Panel de botones
        JPanel buttonPanel = new JPanel();
        configuredButton.setActionCommand(BTN_CHA);
        buttonPanel.add(configuredButton);

        buttonPanel.setOpaque(false);
        infoPanel.add(buttonPanel, BorderLayout.SOUTH);

    }

    /**
     * funcion que sirve para asociar los botones con sus action listeners
     * @param actionListener actionListener para asociar el resultado de los botones, para controlar si se pulsan
     */
    public void configuredController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        jbSettings.addActionListener(actionListener);
        configuredButton.addActionListener(actionListener);
    }

    /**
     * esta función sirve para actualizar la comboBox con las opciones existentes
     * @param options es una cadena de texto con las opciones de juegos existentes
     */
    public void chargeExistingGames(String[] options){
        for(String option: options){
            comboBox.addItem(option);
        }
    }

    /**
     * funcion para poder seleccionar y devolver un elemento de la comboBox
     * @return devuelve el elemento seleccionado en la comboBox en forma de cadena de texto
     */
    public String optionSelected(){
        return (String) comboBox.getSelectedItem();
    }

}
