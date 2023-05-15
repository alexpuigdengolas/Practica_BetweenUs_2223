package presentation.views;

import business.PlayerManager;
import business.entities.character.Character;
import business.entities.character.Npc;
import business.entities.character.Player;
import business.entities.map.Map;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GameView extends JPanel {

    public static final String BTN_L = "BTN_L";
    public static final String BTN_R = "BTN_R";
    public static final String BTN_U = "BTN_U";
    public static final String BTN_D = "BTN_D";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";

    private MapView mapView = new MapView();

    private JButton jbU = new BasicArrowButton(BasicArrowButton.NORTH);
    private JButton jbL = new BasicArrowButton(BasicArrowButton.WEST);
    private JButton jbR = new BasicArrowButton(BasicArrowButton.EAST);
    private JButton jbD = new BasicArrowButton(BasicArrowButton.SOUTH);


    private JButton jbBack = new JButton();
    private JButton jbSettings = new JButton();



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
        jpButtons.setPreferredSize(new Dimension(150, 150));

        jpAux.add(jpButtons);
        this.add(jpAux, BorderLayout.WEST);


        //TODO: Hacer la botonera lateral para el movimiento del jugador
    }

    public void gameController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        jbSettings.addActionListener(actionListener);
        jbU.addActionListener(actionListener);
        jbL.addActionListener(actionListener);
        jbD.addActionListener(actionListener);
        jbR.addActionListener(actionListener);

    }

    public void setMap(Map map, Character userPlayer, LinkedList<Character> npcs) {
        this.mapView.configureMapView(map, userPlayer,npcs);
        this.mapView.setSize(new Dimension(1500, 1500));
        this.add(mapView, BorderLayout.CENTER);
    }

    public void updateMapView(Map map, Character userPlayer,LinkedList<Character>npcs){
        this.mapView.updateMapView(map, userPlayer,npcs);
        this.mapView.setSize(new Dimension(1500, 1500));
        this.add(mapView, BorderLayout.CENTER);
    }
}
