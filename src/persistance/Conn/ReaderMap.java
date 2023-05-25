package persistance.Conn;

import business.entities.map.Map;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;

/**
 * Esta clase nos permite leer los archivos JSON de los mapas
 */
public class ReaderMap {
    private Map map;

    /**
     * Este es el constructor de este mapa
     * @param mapName el nombre del mapa
     */
    public ReaderMap(String mapName) {
        try {
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            File f = new File("");
            String path = f.getAbsolutePath();
            reader = new com.google.gson.stream.JsonReader(new FileReader(path + "/src/mapFiles/" + mapName));
            map = gson.fromJson(reader, Map.class);

            System.out.println("\nLectura del mapa finalitzada.\n");

        } catch (Exception e) {
            System.out.println("No s'ha pogut llegir el mapa: " + e.getMessage());
        }
    }

    /**
     * Este metodo nos permite conseguir un mapa especifico
     * @return el mapa que estamos buscando
     */
    public Map getMap() {
        //System.out.println(map.getMapName() + "estoy en el map reader");
        return map;
    }

}
