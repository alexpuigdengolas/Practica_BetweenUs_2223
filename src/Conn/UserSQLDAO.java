package Conn;

import entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Conn.JsonReader.llegeixJSON;

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
    public void deleteUser(String username) {

    }

    @Override
    public boolean userNameExists(String userName) {
        return false;
    }

    @Override
    public boolean userMailExists(String userMail) {
        return false;
    }

    @Override
    public String getUsername(String loginName) {
        return null;
    }
}
