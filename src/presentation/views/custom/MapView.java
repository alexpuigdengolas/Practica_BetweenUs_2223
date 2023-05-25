package presentation.views.custom;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import business.entities.character.Character;
import business.entities.map.Map;
import presentation.views.PintaMapa;


public class MapView extends JPanel {

    private Map map;

    public MapView() {

    }

    public void configureMapView(Map map, Character userPlayer, LinkedList<Character>npcs){

        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());

        //Creamos el mapa pasando el tamaño del mapa y el mapa
        PintaMapa mp = new PintaMapa(new GridLayout(map.getWidth(), map.getHeight()), map,userPlayer,npcs,false);
        JPanel jpCenter;
        //Pintamos el mapa en el panel
        jpCenter = mp.creaMapa();
        this.add(jpCenter);

    }

    public void updateMapView(Map map, Character userPlayer,LinkedList<Character>npcs, Boolean revealMap) {

        this.removeAll();


        PintaMapa mp = new PintaMapa(new GridLayout(map.getWidth(), map.getHeight()), map,userPlayer,npcs,revealMap);
        JPanel jpCenter;
        //Pintamos el mapa en el panel
        jpCenter = mp.creaMapa();
        this.add(jpCenter);

        jpCenter.revalidate();
        jpCenter.repaint();
    }

}
