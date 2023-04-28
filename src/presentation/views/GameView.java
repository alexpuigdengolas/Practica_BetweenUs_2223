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

    private JButton jbU;
    private JButton jbL;
    private JButton jbR;
    private JButton jbD;


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
