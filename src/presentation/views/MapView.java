package presentation.views;

import javax.swing.*;
import java.awt.*;
import business.entities.map.Map;


public class MapView extends JPanel {

    private Map map;

    public MapView() {

    }

    public void configureMapView(Map map){

        //TODO:Esta es la vista de todo el juego, ahora solo esta el Mapa hay que poner el resto de cosas
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());

        //Creamos el mapa pasando el tamaño del mapa y el mapa
        PintaMapa mp = new PintaMapa(new GridLayout(map.getWidth(), map.getHeight()), map);
        JPanel jpCenter;
        //Pintamos el mapa en el panel
        jpCenter = mp.creaMapa();
        this.add(jpCenter);

    }
}
