package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartView extends JPanel {


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
        this.setLayout(new BorderLayout());

        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        this.add(voidPanel, BorderLayout.EAST);
        this.add(voidPanel, BorderLayout.WEST);

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

        this.add(actionPanel, BorderLayout.NORTH);
        this.add(butonPanel, BorderLayout.CENTER);

        int margin = 50;
        this.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
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
