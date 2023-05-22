package presentation.views;

import presentation.views.custom.BackGroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * sera la clase para construir la vista de settings de nuestro programa
 */
public class SettingsView extends JPanel {

    public static final String BTN_LOG= "BTN_LOG";
    public static final String BTN_DEL= "BTN_DEL";
    public static final String BTN_BACK= "BTN_BACK";

    private JButton jbLogOut = new JButton("Log out");
    private JButton jbDelete = new JButton("Delete");
    private JButton jbBack = new JButton("Back");

    private MainView mainView;

    private CardLayout components;

    /**
     * este es el constructor de settings
     */
    public SettingsView() {
        configureView();
    }

    /**
     * En este metodo creamos la vista de settings
     */
    private void configureView() {
        BackGroundPanel fondo = new BackGroundPanel("files/images/background.jpg");
        fondo.setLayout(new BorderLayout());

        this.setLayout(new BorderLayout());
        this.add(fondo);

        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        voidPanel.setOpaque(false);
        fondo.add(voidPanel, BorderLayout.EAST);
        fondo.add(voidPanel, BorderLayout.WEST);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        jbBack.setActionCommand(BTN_BACK);
        actionPanel.add(jbBack);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        jbDelete.setActionCommand(BTN_DEL);
        jbLogOut.setActionCommand(BTN_LOG);
        jbDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbLogOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(jbDelete);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(jbLogOut);

        actionPanel.setOpaque(false);
        fondo.add(actionPanel, BorderLayout.NORTH);

        buttonPanel.setOpaque(false);
        fondo.add(buttonPanel, BorderLayout.CENTER);

        int margin = 50;
        fondo.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
    }

    /**
     * Esta funcion se encarga de vincular el listener con los botones de la vista
     * @param controller sirve para conectar la respuesta de pulsar un boton para asociarlos
     */
    public void settingsController(ActionListener controller) {
        jbLogOut.addActionListener(controller);
        jbDelete.addActionListener(controller);
        jbBack.addActionListener(controller);
    }

    /**
     * Setter de la vista principal
     * @param mainView vista principal
     */
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Setter de los componentes de las vistas
     * @param components componentes de las vistas
     */
    public void setComponents(CardLayout components) {
        this.components = components;
    }


    public int confirmPopUp(String action) {
        return JOptionPane.showConfirmDialog(null, action);
    }
}
