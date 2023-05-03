package persistance.Conn;

import business.entities.Game;
import business.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static persistance.Conn.JsonReader.llegeixJSON;

public class UserSQLDAO implements UserDAO {
    private Data data;
    private DBConnector conn;
    public UserSQLDAO() {
        data = llegeixJSON();
        conn = new DBConnector(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
    }
    @Override
    public void registerUser(User user) {
        conn.connect();
        conn.insertQuery("INSERT INTO User(username, email, password, partides_guanyades, partides_jugades) VALUES (" + "'" + user.getName() + "'" + "," + "'" + user.getMail() + "'" + "," + "'" + user.getPassword() + "'" + ",0,0)");
        conn.disconnect();

    }

    @Override
    public boolean checkLoginUser(String userNameMail, String password) {
        conn.connect();
        String query = "SELECT u.username FROM User AS u WHERE (u.username LIKE '" + userNameMail + "' OR u.email LIKE '" + userNameMail + "') AND u.password LIKE '" + password + "'";
        ResultSet rs = conn.selectQuery(query);
        try {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void deleteUser(String nameLogin) {
        conn.connect();
        String query = "DELETE FROM User AS u WHERE (u.username LIKE '" + nameLogin + "' OR u.email LIKE '" + nameLogin + "')";
        conn.deleteQuery(query);
        conn.disconnect();
    }

    @Override
    public boolean userNameExists(String userName) {
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT u.username FROM User AS u WHERE u.username LIKE '" + userName + "'");
        try {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean userMailExists(String userMail) {
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT u.email FROM User AS u WHERE u.email LIKE '" + userMail + "'");
        try {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getUsername(String loginName) {
        if (userMailExists(loginName)) {
            conn.connect();
            ResultSet rs = conn.selectQuery("SELECT u.username FROM User AS u WHERE u.email LIKE '" + loginName + "'");
            try {
                if(rs.next()) {
                    return rs.getString("username");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        } else {
            return loginName;
        }
    }

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
}
