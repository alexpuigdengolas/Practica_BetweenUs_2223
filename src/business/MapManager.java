package business;

import business.entities.map.Cell;
import business.entities.map.Map;
import persistance.Conn.ReaderMap;

/**
 * La classe 'MapManager' serveix per gestionar i llegir el mapa de la partida
 *
 * Els mètodes implementats ens retornen informació del mapa en funció dels
 * paràmetres que els hi passem a aquests primers
 */
public class MapManager {
    // Attributes
    private static ReaderMap mapReader;

    private PlayerManager playerManager;
    private Map map;

    // Parametrized constructor
    public MapManager(Map map) {
        this.map = map;

    }

    /**
     * Metodo para leer el mapa desde el JSON
     * @param mapName nombre del mapa
     * @return El mapa al que queremos acceder
     */
    //Funcion que llama al reader el json del mapa y nos devuelve el mapa qe queriamos
    public static Map llegeixMapa(String mapName) {
        mapReader = new ReaderMap(mapName);
        return mapReader.getMap();
    }

    /**
     * Setter del gestor de jugadores
     * @param playerManager el gestor de jugadores
     */
    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Getter del mapa
     * @return el mapa
     */
    public Map getMap() {
        return map;
    }

    /**
     * Metodo para conseguir los jugadores de la siguiente partida
     * @param nextCell la siguiente casilla
     * @return la casilla en si
     */
    public Cell nextPlayerCell (int[] nextCell) {
        return getMap().getCellByCoordinates(nextCell);
    }

    /**
     * Metodo para saber donde se encuentra nuestro jugador
     * @return La casilla de nuestro jugador
     */
    public Cell userPlayerCell () {
        for(int i = 0; i < map.getCells().size(); i++) {
            if (map.getCells().get(i) == playerManager.getPlayer().getCell()) {
                return map.getCells().get(i);
            }
        }
        return null;
    }
}


