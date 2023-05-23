package persistance.Conn;

import business.entities.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static persistance.Conn.JsonReader.llegeixJSON;

public class GameSQLDAO implements GameDAO{

    private Data data;
    private DBConnector conn;
    public GameSQLDAO() {
        data = llegeixJSON();
        conn = new DBConnector(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
    }
    public void saveGame(Game game){
        conn.connect();
        conn.insertQuery("INSERT INTO Game(gameName, players, impostors, playerColor, map, creator) VALUES ('" + game.getGameName() + "','" + game.getPlayers() + "','" + game.getImpostors() + "','" + game.getColor() + "','"+ game.getMap() + "' ,'"+ game.getCreator() + "')");
        conn.disconnect();
    }

    public ArrayList<String> getGames(){

        ArrayList<String> games = new ArrayList<>();

        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT g.gameName FROM game AS g");
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

    public void deleteGame(String game){
        conn.connect();
        conn.deleteQuery("DELETE FROM game AS g WHERE g.gameName LIKE '" + game + "'");
        conn.disconnect();
    }

    public void deleteGames(String user){
        conn.connect();
        conn.deleteQuery("DELETE FROM game AS g WHERE g.creator LIKE '" + user + "'");
        conn.disconnect();
    }

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

    @Override
    public void setGameStatistic(String user, int game, float percentage) {
        conn.connect();
        conn.insertQuery("INSERT INTO PlayerStatistics(username, game, percentage) VALUES ('" + user + "','" + (game+1) + "','" + percentage +"')");
        conn.disconnect();
    }


}
