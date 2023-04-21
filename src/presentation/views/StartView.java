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
        actionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // set left alignment
        jbSettings.setActionCommand(BTN_STI);
        actionPanel.add(jbSettings);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        jbNewGame.setActionCommand(BTN_CRE);
        jbConfGame.setActionCommand(BTN_CON);
        jbCharGame.setActionCommand(BTN_CHAR);
        jbDellGame.setActionCommand(BTN_DEL);

        buttonPanel.add(jbNewGame);
        buttonPanel.add(jbConfGame);
        buttonPanel.add(jbCharGame);
        buttonPanel.add(jbDellGame);

        this.add(actionPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);

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
