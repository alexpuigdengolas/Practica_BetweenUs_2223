package presentation.views;

import business.entities.character.Character;
import business.entities.map.Map;
import presentation.views.custom.DeductionPanel;
import presentation.views.custom.DefaultTaskPanel;
import presentation.views.custom.MapView;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameView extends JPanel {

    public static final String BTN_L = "BTN_L";
    public static final String BTN_R = "BTN_R";
    public static final String BTN_U = "BTN_U";
    public static final String BTN_D = "BTN_D";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";
    public static final String BTN_RVL = "BTN_RVL";

    private MapView mapView = new MapView();
    private JPanel jpTask = new JPanel();
    private DefaultTaskPanel defaultTaskPanel;
    private DeductionPanel deductionPanel;
    private CardLayout viewComponents;
    private JButton showDeductionsButton;
    private JButton comprovar;

    private JButton jbU = new BasicArrowButton(BasicArrowButton.NORTH);
    private JButton jbL = new BasicArrowButton(BasicArrowButton.WEST);
    private JButton jbR = new BasicArrowButton(BasicArrowButton.EAST);
    private JButton jbD = new BasicArrowButton(BasicArrowButton.SOUTH);

    private JButton jbReveal = new JButton("Reveal Map");


    private JButton jbBack = new JButton();
    private JButton jbSettings = new JButton();



    public GameView() {
        defaultTaskPanel = new DefaultTaskPanel();
        deductionPanel = new DeductionPanel(new ArrayList<String>());

        // Create a new JPanel with CardLayout
        JPanel cardPanel = new JPanel(new CardLayout());

        // Add jpTask to the cardPanel
        cardPanel.add(jpTask, "jpTask");

        // Add panels to jpTask
        jpTask.setLayout(new BorderLayout());
        //jpTask.add(defaultTaskPanel, BorderLayout.CENTER);
        jpTask.add(deductionPanel);

        // Add cardPanel to the GameView
        this.setLayout(new BorderLayout());
        this.add(cardPanel, BorderLayout.CENTER);

        configureView();

        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "jpTask");

    }


    private CardLayout getViewComponent() {
        return viewComponents;
    }

    private void configureView(){
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

        //TODO:ARREGLAR ESTE BOTON
        jbReveal.setActionCommand(BTN_RVL);
        jpActions.add(jbReveal);

        this.add(jpActions, BorderLayout.NORTH);

        JPanel jpAux = new JPanel();
        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new BorderLayout());
        jbD.setActionCommand(BTN_D);
        //jbD.setPreferredSize(new Dimension(50, 75 ));
        jbL.setActionCommand(BTN_L);
        //jbL.setPreferredSize(new Dimension(50, 75));
        jbR.setActionCommand(BTN_R);
        //jbR.setPreferredSize(new Dimension(50, 75));
        jbU.setActionCommand(BTN_U);
        jbU.setPreferredSize(new Dimension(150, 75));
        jpButtons.add(jbU, BorderLayout.NORTH);
        jpButtons.add(jbL, BorderLayout.WEST);
        jpButtons.add(jbR, BorderLayout.EAST);
        jpButtons.add(jbD, BorderLayout.CENTER);
        jpButtons.setSize(new Dimension(420, 320));

        jpAux.add(jpButtons);
        this.add(jpAux, BorderLayout.WEST);

        jpTask = new DefaultTaskPanel();
        jpTask.setPreferredSize(new Dimension(420, 100));
        this.add(jpTask, BorderLayout.SOUTH);
    }

    public void gameController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        jbSettings.addActionListener(actionListener);
        jbU.addActionListener(actionListener);
        jbL.addActionListener(actionListener);
        jbD.addActionListener(actionListener);
        jbR.addActionListener(actionListener);
        jbReveal.addActionListener(actionListener);
        this.setFocusable(true);
        this.addKeyListener((KeyListener) actionListener);
    }

    public void setMap(Map map, Character userPlayer, LinkedList<Character> npcs) {
        this.mapView.configureMapView(map, userPlayer,npcs);
        this.mapView.setSize(new Dimension(1500, 1500));
        this.add(mapView, BorderLayout.CENTER);
    }

    public void updateMapView(Map map, Character userPlayer,LinkedList<Character>npcs, Boolean revealMap){
        this.mapView.updateMapView(map, userPlayer,npcs,revealMap);
        this.mapView.setSize(new Dimension(1500, 1500));
        this.add(mapView, BorderLayout.CENTER);
    }

    public void showDefaultTask(){
        CardLayout cardLayout = (CardLayout) jpTask.getLayout();
        cardLayout.show(jpTask, "defaultTask");
    }

    public void comprovaBoto(ArrayList<String> colors){
        showDeductionsButton = new JButton("Show Deductions");
        showDeductionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeductions(colors);
            }
        });
        jpTask.add(showDeductionsButton, BorderLayout.CENTER);

    }
    public void showDeductions(ArrayList<String> colors){
        //showDeductionsButton.setVisible(false);
        deductionPanel = new DeductionPanel(colors);

        // Set CardLayout as the layout manager for jpTask
        jpTask.setLayout(new CardLayout());

        // Add deductionPanel to jpTask
        jpTask.add(deductionPanel, "deduction");

        // Show the deductionPanel using CardLayout
        CardLayout cardLayout = (CardLayout) jpTask.getLayout();
        cardLayout.show(jpTask, "deduction");

        jpTask.setPreferredSize(new Dimension(420, 100));
        this.add(jpTask, BorderLayout.SOUTH);
        this.repaint();
    }
}
