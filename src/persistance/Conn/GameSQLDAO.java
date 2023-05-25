package persistance.Conn;

import business.entities.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static persistance.Conn.JsonReader.llegeixJSON;

/**
 * Esta clase empleara nuestro GameDAO implementando todos sus metodos y dando acceso al apartado de usuario de la base de datos
 */
public class GameSQLDAO implements GameDAO{

    private final Data data;
    private final DBConnector conn;

    /**
     * Este es el constructor de nuestra base de datos
     */
    public GameSQLDAO() {
        data = llegeixJSON();
        conn = new DBConnector(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
    }

    /**
     * Este metodo nos permite guardar una partida en nuestra base de datos
     * @param game la partida que queremos guardar
     */
    @Override
    public void saveGame(Game game){
        conn.connect();
        conn.insertQuery("INSERT INTO Game(gameName, players, impostors, playerColor, map, creator) VALUES ('" + game.getGameName() + "','" + game.getPlayers() + "','" + game.getImpostors() + "','" + game.getColor() + "','"+ game.getMap() + "' ,'"+ game.getCreator() + "')");
        conn.disconnect();
    }

    /**
     * Este metodo nos permite conseguir todas las partidas de nuestra base de datos
     * @param user el usuario que ha creado estas partidas y esta asociado a ellas en la bases de datos
     * @return las partidas de este usuario
     */
    @Override
    public ArrayList<String> getGames(String user){

        ArrayList<String> games = new ArrayList<>();

        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT g.gameName FROM game AS g WHERE g.creator LIKE '" + user + "'");

        try{
            while (rs.next()) {
                String gameName = rs.getString("gameName");
                games.add(gameName);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();

        return games;
    }

    /**
     * Este metodo nos permite buscar una partida especifica por su nombre
     * @param game nombre de la partida
     * @return la partida en si
     */
    @Override
    public Game searchGame(String game) {
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT * FROM game AS g WHERE g.gameName LIKE '" + game + "'");
        Game games = null;
        try {
            if (rs != null && rs.next()) {
                String nom = (rs.getString("gameName"));
                int players = (rs.getInt("players"));
                int impostors = (rs.getInt("impostors"));
                String color = (rs.getString("playerColor"));
                String map = (rs.getString("map"));
                String cretor = (rs.getString("creator"));
                games = new Game(nom, players, impostors, color, map, cretor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        return games;
    }

    /**
     * Metodo para eliminar una partida especifica
     * @param game el nombre de la partida que queremos eliminar
     */
    @Override
    public void deleteGame(String game){
        conn.connect();
        conn.deleteQuery("DELETE FROM game AS g WHERE g.gameName LIKE '" + game + "'");
        conn.disconnect();
    }

    /**
     * Metodo para eliminar todas las partidas relacionadas con un usuario
     * @param user el usuario del cual queremos eliminar sus partidas
     */
    @Override
    public void deleteGames(String user){
        conn.connect();
        conn.deleteQuery("DELETE FROM game AS g WHERE g.creator LIKE '" + user + "'");
        conn.disconnect();
    }

    /**
     * Este metodo nos permite buscar las estadisticas de la partida
     * @param user el usuario del cual queremos sus estadisticas
     * @return listado con todos los valores porcentuales de las estadisticas
     */
    @Override
    public ArrayList<Float> searchGameStatistics(String user) {
        ArrayList<Float> statistics = new ArrayList<>();

        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT p.percentage FROM PlayerStatistics AS p WHERE p.username LIKE '"+user+"'");
        try{
            while (rs.next()) {
                Float statistic = rs.getFloat("percentage");
                statistics.add(statistic);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();

        return statistics;
    }

    /**
     * Metodo para añadir estadisticas a una partida
     * @param user el usuario del cual queremos las estadisticas
     * @param game nombre de la partida
     * @param percentage porcentaje despues del resultado de la estadistica
     */
    @Override
    public void setGameStatistic(String user, String game, float percentage) {
        conn.connect();
        conn.insertQuery("INSERT INTO PlayerStatistics(username, game, percentage) VALUES ('" + user + "','" + game + "','" + percentage +"')");
        conn.disconnect();
    }

    /**
     * Metodo para comprobar si un nombre de una partida ya existe
     * @param gameName el nombre de la partida
     * @return resultado de nuestra busqueda
     */
    @Override
    public boolean gameNameExists(String gameName){
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT g.gameName FROM Game AS g WHERE g.gameName LIKE '" + gameName + "'");
        try {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
