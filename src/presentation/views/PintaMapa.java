package presentation.views;



import business.entities.character.Character;
import business.entities.character.Npc;
import business.entities.map.Cell;
import business.entities.map.Map;
import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;


//Esta clase pinta el mapa general cada vez que la llamemos, en un futuro le pasaremos todos los personajes y el resto de cosas para jugar

/**
 * sera la clase para pintar el mapa general.
 */
public class PintaMapa extends JPanel {
    private Color color;
    private final Map map;
    private JPanel room;
    private JPanel jpMapa;
    private final Character userPlayer;
    private final LinkedList<Character> npcs;
    private final Boolean revealMap;


    /**
     * Contructor de la classe PintaMapa
     * @param layoutManager
     * @param map
     * @param userPlayer
     * @param npcs
     * @param revealMap
     */
    public PintaMapa(LayoutManager layoutManager, Map map, Character userPlayer,LinkedList<Character>npcs,Boolean revealMap) {
        super(layoutManager);
        this.map = map;
        this.userPlayer = userPlayer;
        this.npcs = npcs;
        this.revealMap = revealMap;
    }

    /**
     * Recorre cada posició del mapa i, segons el tipus d'espai (passadís o habitació), crea un panell corresponent amb colors i característiques específiques.
     * @return jpMapa es el panell del mapa.
     */
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
                        room = new PintaHab(color,roomName,colors,whereUserPosition(userPlayer.getCell(), map.getCells().get(pos)), revealMap,corpses);
                        room.setBorder(BorderFactory.createLineBorder(Color.WHITE));//pintem els borders
                        jpMapa.add(room);
                    }
                    //Repetimos el proceso con el pasillo, pero no hace falta saber que pasillo es, le pasamos la movilidad para saber hacia donde va el pasillo
                    if (map.getCells().get(pos).getType().equals("corridor")) {
                        JPanel corridor = new PintaPassadis(map.getCells().get(pos).getMobility(),colors,whereUserPosition(userPlayer.getCell(), map.getCells().get(pos)), revealMap,corpses);

                        corridor.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        jpMapa.add(corridor);
                    }
                }
            }
        }
        return jpMapa;
    }

    /**
     * Detecta quins jugadors (npc o no) es troben dins la sala i ho guarda en una llista.
     * @param userPlayer
     * @param npcs
     * @param cell
     * @return colors llista de colors dels personatges que es troben a les sales.
     */
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

    /**
     * Comprova si hi han cadavers dins les sales i retorna una llista de booleans.
     * @param userPlayer
     * @param characters
     * @param cell
     * @return corpses Llista de booleans que indica si hi han cadavers o no a una sala
     */
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

    /**
     * Comparem si la sala del usuari es la mateixa que una altra
     * @param userCell
     * @param cell
     * @return userCell retorna verdader o fals depenent del que doni la comparació.
     */
    public boolean whereUserPosition(Cell userCell, Cell cell) {
        return userCell == cell;
    }


}
