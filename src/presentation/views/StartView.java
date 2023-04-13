package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartView extends JFrame {


    public static final String BTN_STI= "BTN_STI";
    public static final String BTN_CRE= "BTN_CRE";
    public static final String BTN_CON= "BTN_CON";
    public static final String BTN_CHAR= "BTN_CHAR";
    public static final String BTN_DEL= "BTN_DEL";


    private JButton jbSettings = new JButton();
    private JButton jbNewGame = new JButton("New Game");
    private JButton jbConfGame = new JButton("Configured Game");
    private JButton jbCharGame = new JButton("Charged Game");
    private JButton jbDellGame = new JButton("Delete Game");


    private MainView mainView;

    private CardLayout components;




    public StartView(){

        configureView();

    }

    /**
     * En esta funcion construimos la vista
     */
    private void configureView() {

        JPanel actionPanel= new JPanel();

        jbSettings.setActionCommand(BTN_STI);
        actionPanel.add(jbSettings);

        JPanel butonPanel = new JPanel();

        butonPanel.setLayout(new BoxLayout(butonPanel, BoxLayout.Y_AXIS));

        jbNewGame.setActionCommand(BTN_CRE);

        jbConfGame.setActionCommand(BTN_CON);

        jbCharGame.setActionCommand(BTN_CHAR);

        jbDellGame.setActionCommand(BTN_DEL);

        butonPanel.add(jbNewGame);
        butonPanel.add(jbConfGame);
        butonPanel.add(jbCharGame);
        butonPanel.add(jbDellGame);

    }

    /**
     * Esta funcion se encarga de vincular el listener con los botones de la vista
     *
     * @param actionListener listener de los botones
     */

    public void startController(ActionListener actionListener){

        jbSettings.addActionListener(actionListener);
        jbNewGame.addActionListener(actionListener);
        jbConfGame.addActionListener(actionListener);
        jbCharGame.addActionListener(actionListener);
        jbDellGame.addActionListener(actionListener);

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
