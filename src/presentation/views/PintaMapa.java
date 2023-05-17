package presentation.views;



import business.entities.character.Character;
import business.entities.character.Npc;
import business.entities.map.Cell;
import business.entities.map.Map;
import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;


//Esta clase pinta el mapa general cada vez que la llamemos, en un futuro le pasaremos todos los personajes y el resto de cosas para jugar
public class PintaMapa extends JPanel {
    private Color color;
    private final Map map;
    private JPanel room;
    private JPanel jpMapa;
    private Character userPlayer;
    private LinkedList<Character> npcs;


    public PintaMapa(LayoutManager layoutManager, Map map, Character userPlayer,LinkedList<Character>npcs) {
        super(layoutManager);
        this.map = map;
        this.userPlayer = userPlayer;
        this.npcs = npcs;
    }


    public JPanel creaMapa() {
        jpMapa = new JPanel(new GridLayout(map.getWidth(),map.getHeight()));

        //Recorremos el mapa cuadrado por cuadrado
        for (int i = 0; i < map.getHeight(); ++i) {
            for (int j = 0; j < map.getWidth(); ++j) {
                int pos = -1;
                //Pintamos cada parte del mapa en funcion de su posicion
                //Primero miramos que tipo de espacio es si pasillo o Hab
                for (int m = 0; m < map.getCells().size(); m++) {
                    if (map.getCells().get(m).getX() == j && map.getCells().get(m).getY() == i)
                        pos = m;
                }
                if (pos == -1) { //Si no es nada es espacio negro
                    JPanel empty = new JPanel();
                    empty.setBackground(Color.black);
                    empty.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    jpMapa.add(empty);
                } else {
                    LinkedList<String> colors = getCellColors(userPlayer,npcs, map.getCells().get(pos));
                    LinkedList<Boolean> corpses = getCellCorpses(userPlayer, npcs, map.getCells().get(pos));

                        //Miramos si es un cuarto
                    if (map.getCells().get(pos).getType().equals("room")) {
                        try {
                            color = (Color) Color.class.getField(map.getCells().get(pos).getColor()).get(null);

                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        String roomName = map.getCells().get(pos).getRoomName();

                        //Llamamos a la clase que se encarga de pintar los cuartos, con el color y el nombre del cuarto(Aqui se tendran que pasar mas cosas)
                        room = new PintaHab(color,roomName,colors,whereUserPosition(userPlayer.getCell(), map.getCells().get(pos)), true,corpses);
                        room.setBorder(BorderFactory.createLineBorder(Color.WHITE));//pintem els borders
                        jpMapa.add(room);
                    }
                    //Repetimos el proceso con el pasillo, pero no hace falta saber que pasillo es, le pasamos la movilidad para saber hacia donde va el pasillo
                    if (map.getCells().get(pos).getType().equals("corridor")) {
                        JPanel corridor = new PintaPassadis(map.getCells().get(pos).getMobility(),colors,whereUserPosition(userPlayer.getCell(), map.getCells().get(pos)), true,corpses);

                        corridor.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        jpMapa.add(corridor);
                    }
                }
            }
        }
        return jpMapa;
    }
    public LinkedList<String> getCellColors(Character userPlayer, LinkedList<Character> npcs, Cell cell) {
        LinkedList<String> colors = new LinkedList<>();
        if (userPlayer.getCell() == cell) {
            colors.add(userPlayer.getColor());
        }
        for (Character character: npcs) {
            if (cell == character.getCell()) {
                colors.add(character.getColor());
            }
        }
        return colors;
    }

    public LinkedList<Boolean> getCellCorpses(Character userPlayer, LinkedList<Character> characters, Cell cell) {
        LinkedList<Boolean> corpses = new LinkedList<>();
        if (userPlayer.getCell() == cell) {
            corpses.add(false);
        }
        for (Character character: characters) {
            if (cell == character.getCell()) {
                if(character.isDead()) {
                    corpses.add(true);
                } else {
                    corpses.add(false);
                }
            }
        }
        return corpses;
    }
    public boolean whereUserPosition(Cell userCell, Cell cell) {
        return userCell == cell;
    }


}
