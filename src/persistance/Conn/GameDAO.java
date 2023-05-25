package persistance.Conn;

import business.entities.Game;

import java.util.ArrayList;

/**
 * Esta interficie implementa todos los metodos de la game DAO
 */
public interface GameDAO {

    /**
     * Este metodo nos permite guardar una partida en nuestra base de datos
     * @param game la partida que queremos guardar
     */
    void saveGame(Game game);

    /**
     * Este metodo nos permite conseguir todas las partidas de nuestra base de datos
     * @param user el usuario que ha creado estas partidas y esta asociado a ellas en la bases de datos
     * @return las partidas de este usuario
     */
    ArrayList<String> getGames(String user);

    /**
     * Este metodo nos permite buscar una partida especifica por su nombre
     * @param game nombre de la partida
     * @return la partida en si
     */
    Game searchGame(String game);

    /**
     * Metodo para eliminar una partida especifica
     * @param game el nombre de la partida que queremos eliminar
     */
    void deleteGame(String game);

    /**
     * Metodo para eliminar todas las partidas relacionadas con un usuario
     * @param user el usuario del cual queremos eliminar sus partidas
     */
    void deleteGames(String user);

    /**
     * Este metodo nos permite buscar las estadisticas de la partida
     * @param user el usuario del cual queremos sus estadisticas
     * @return listado con todos los valores porcentuales de las estadisticas
     */
    ArrayList<Float> searchGameStatistics(String user);

    /**
     * Metodo para añadir estadisticas a una partida
     * @param user el usuario del cual queremos las estadisticas
     * @param game nombre de la partida
     * @param percentage porcentaje despues del resultado de la estadistica
     */
    void setGameStatistic(String user, String game, float percentage);

    /**
     * Metodo para comprobar si un nombre de una partida ya existe
     * @param gameName el nombre de la partida
     * @return resultado de nuestra busqueda
     */
    boolean gameNameExists(String gameName);

}
