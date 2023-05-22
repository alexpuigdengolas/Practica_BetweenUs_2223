package presentation.views;

import presentation.views.custom.BackGroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * sera la clase para construir la vista del principio de nuestro programa, la vista StartView
 */
public class StartView extends JPanel {


    public static final String BTN_STI= "BTN_STI";
    public static final String BTN_CRE= "BTN_CRE";
    public static final String BTN_CON= "BTN_CON";
    public static final String BTN_CHAR= "BTN_CHAR";
    public static final String BTN_DEL= "BTN_DEL";
    public static final String BTN_STA = "BTN_STA";


    private JButton jbSettings = new JButton("Settings");
    private JButton jbNewGame = new JButton("New Game");
    private JButton jbConfGame = new JButton("Configured Game");
    private JButton jbCharGame = new JButton("Charged Game");
    private JButton jbDellGame = new JButton("Delete Game");
    private JButton jbStatistics = new JButton("Statistics");


    private MainView mainView;

    private CardLayout components;


    /**
     * Este es nuestro constructor de Start
     */
    public StartView(){

        configureView();

    }

    /**
     * En esta funcion construimos la vista de startView
     */
    private void configureView() {

        BackGroundPanel fondo = new BackGroundPanel("files/images/background.jpg");
        fondo.setLayout(new BorderLayout());


        this.setLayout(new BorderLayout());
        this.add(fondo);

        JPanel auxPanel = new JPanel();
        auxPanel.setLayout(new BorderLayout());
        auxPanel.setOpaque(false);

        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        voidPanel.setOpaque(false);
        fondo.add(voidPanel, BorderLayout.EAST);
        fondo.add(voidPanel, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        JLabel jlTitle = new JLabel("Between Us");
        jlTitle.setForeground(Color.WHITE);
        jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Serif", Font.PLAIN, 40));
        titlePanel.add(jlTitle);
        titlePanel.setOpaque(false);
        auxPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel actionPanel= new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // set left alignment
        jbSettings.setActionCommand(BTN_STI);
        actionPanel.add(jbSettings);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        //centrar els botons
        jbNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbConfGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbCharGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbDellGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);

        jbNewGame.setActionCommand(BTN_CRE);
        jbConfGame.setActionCommand(BTN_CON);
        jbCharGame.setActionCommand(BTN_CHAR);
        jbDellGame.setActionCommand(BTN_DEL);
        jbStatistics.setActionCommand(BTN_STA);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(jbNewGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(jbConfGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(jbCharGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(jbDellGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(jbStatistics);

        actionPanel.setOpaque(false);
        fondo.add(actionPanel, BorderLayout.NORTH);
        buttonPanel.setOpaque(false);
        auxPanel.add(buttonPanel, BorderLayout.CENTER);
        fondo.add(auxPanel);

        int margin = 50;
        fondo.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
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
        jbStatistics.addActionListener(actionListener);
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


