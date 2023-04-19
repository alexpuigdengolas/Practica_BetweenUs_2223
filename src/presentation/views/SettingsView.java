package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingsView extends JPanel {

    public static final String BTN_LOG= "BTN_LOG";
    public static final String BTN_DEL= "BTN_DEL";
    public static final String BTN_BACK= "BTN_BACK";

    private JButton jbLogOut = new JButton("Log out");
    private JButton jbDelete = new JButton("Delete");
    private JButton jbBack = new JButton("");

    private MainView mainView;

    private CardLayout components;

    public SettingsView() {
        configureView();
    }

    private void configureView(){
        this.setLayout(new BorderLayout());

        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        this.add(voidPanel, BorderLayout.EAST);
        this.add(voidPanel, BorderLayout.WEST);

        JPanel actionPanel= new JPanel();

        jbBack.setActionCommand(BTN_BACK);
        actionPanel.add(jbBack);

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        jbDelete.setActionCommand(BTN_DEL);
        jbLogOut.setActionCommand(BTN_LOG);

        buttonPanel.add(jbDelete);
        buttonPanel.add(jbLogOut);

        this.add(actionPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);

        int margin = 50;
        this.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
    }

    public void settingsController(ActionListener controller){
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
}
