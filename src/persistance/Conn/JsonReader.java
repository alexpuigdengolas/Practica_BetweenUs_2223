package persistance.Conn;

import com.google.gson.Gson;


import java.io.FileReader;


/**
 * La classe ens permet llegir i guardar el json que necesitem
 */
public class JsonReader {
    private static Data data = new Data();

    /**
     * Mètode que llegeix la informació del json
     * @return informació del json en una classe Data
     */
    public static Data llegeixJSON(){
        try{
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            reader = new com.google.gson.stream.JsonReader(new FileReader("files/config.json"));
            data = gson.fromJson(reader, Data.class);

        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }
        return data;
    }
}