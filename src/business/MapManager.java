package business;

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


    //Funcion que llama al reader el json del mapa y nos devuelve el mapa qe queriamos
    public static Map llegeixMapa(String mapName) {
        mapReader = new ReaderMap(mapName);
        return mapReader.getMap();
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

}

