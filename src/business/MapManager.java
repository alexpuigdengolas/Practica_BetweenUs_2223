package business;

import business.entities.map.Cell;
import com.google.gson.Gson;
import business.entities.map.Map;
import persistance.Conn.ReaderMap;


import java.io.File;
import java.io.FileReader;

/**
 * La classe 'MapManager' serveix per gestionar i llegir el mapa de la partida
 *
 * Els mètodes implementats ens retornen informació del mapa en funció dels
 * paràmetres que els hi passem a aquests primers
 */
public class MapManager {
    // Attributes
    private static ReaderMap mapReader;

    // Parametrized constructor
    public MapManager() {

    }


    //Funcion que llama al reader el json del mapa y nos devuelve el mapa qe queriamos
    public static Map llegeixMapa(String mapName) {
        mapReader = new ReaderMap(mapName);
        return mapReader.getMap();
    }

}


