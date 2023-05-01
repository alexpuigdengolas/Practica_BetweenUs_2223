package presentation.views;

import business.entities.map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView extends JPanel {

    public static final String BTN_L = "BTN_L";
    public static final String BTN_R = "BTN_R";
    public static final String BTN_U = "BTN_U";
    public static final String BTN_D = "BTN_D";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";

    private MapView mapView = new MapView();

    private JButton jbU = new JButton();
    private JButton jbL = new JButton();
    private JButton jbR = new JButton();
    private JButton jbD = new JButton();


    private JButton jbBack = new JButton();
    private JButton jbSettings = new JButton();

    private Map map;


    public GameView(){
        configureView();
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

        this.add(jpActions, BorderLayout.NORTH);

        JPanel jpAux = new JPanel();
        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new BorderLayout());
        jbD.setActionCommand(BTN_D);
        jbL.setActionCommand(BTN_L);
        jbR.setActionCommand(BTN_R);
        jbU.setActionCommand(BTN_U);
        jpButtons.add(jbU, BorderLayout.NORTH);
        jpButtons.add(jbL, BorderLayout.EAST);
        jpButtons.add(jbR, BorderLayout.WEST);
        jpButtons.add(jbD, BorderLayout.CENTER);

        jpAux.setSize(new Dimension(500, 500));
        jpAux.add(jpButtons);
        this.add(jpAux, BorderLayout.WEST);


        //TODO: Hacer la botonera lateral para el movimiento del jugador
    }

    public void gameController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        jbSettings.addActionListener(actionListener);
        //jbU.addActionListener(actionListener);
        //jbL.addActionListener(actionListener);
        //jbD.addActionListener(actionListener);
        //jbR.addActionListener(actionListener);

    }

    public void setMap(Map map) {
        this.mapView.configureMapView(map);
        this.add(mapView, BorderLayout.CENTER);
    }
}
