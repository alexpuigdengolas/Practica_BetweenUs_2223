package persistance.Conn;

import business.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;

import static persistance.Conn.JsonReader.llegeixJSON;

public class UserSQLDAO implements UserDAO {
    private final Data data;
    private final DBConnector conn;
    public UserSQLDAO() {
        data = llegeixJSON();
        conn = new DBConnector(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
    }

    /**
     * Este metodo nos permite registrar nuevos usuarios en la base de datos
     * @param user el usuario a registrar
     */
    @Override
    public void registerUser(User user) {
        conn.connect();
        conn.insertQuery("INSERT INTO User(username, email, password, partides_guanyades, partides_jugades) VALUES (" + "'" + user.getName() + "'" + "," + "'" + user.getMail() + "'" + "," + "'" + user.getPassword() + "'" + ",0,0)");
        conn.disconnect();

    }

    /**
     * Este metodo nos permite comprobar que los datos de login de un usuario son correctos
     * @param userNameMail el nombre o correo de login
     * @param password la contraseña
     * @return si se ha podido hacer login o no
     */
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

    /**
     * Metodo para poder eliminar un usuario
     * @param nameLogin el nombre del usuario que queremos eliminar
     */
    @Override
    public void deleteUser(String nameLogin) {
        conn.connect();
        String query = "DELETE FROM User AS u WHERE (u.username LIKE '" + nameLogin + "' OR u.email LIKE '" + nameLogin + "')";
        conn.deleteQuery(query);
        conn.disconnect();
    }

    /**
     * Metodo para comprobar si un nombre de usuario existe
     * @param userName el nombre del usuario
     * @return si el nombre existe o no
     */
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

    /**
     * Metodo para comprobar si el correo de usuario existe
     * @param userMail el correo
     * @return si el correo existe o no
     */
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

    /**
     * Metodo para conseguir el nombre de usuario
     * @param loginName nombre de usuario o correo del usuario
     * @return el nombre del usuario
     */
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



    //#nuevo
    /**
     * Este metodo nos permitira conseguir el numero de partidas
     * @param user nombre del usuario
     * @return el numero de partidas
     */
    @Override
    public int getNumGames(String user){
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT partides_jugades FROM User AS p WHERE username LIKE '"+user+"'");

        try {
            if(rs.next()) {
                return rs.getInt("partides_jugades");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conn.disconnect();
        return -1;

    }

    //#nuevo
    /**
     * Metodo para conseguir el numero de victorias de un usuario
     * @param user el nombre del usuario
     * @return el numero de victorias del usuario
     */
    @Override
    public int getNumVictories(String user){
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT partides_guanyades FROM User AS p WHERE username LIKE '"+user+"'");

        try{
            if(rs.next()) {
                return rs.getInt("partides_guanyades");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        conn.disconnect();
        return -1;

    }

    //#nuevo
    /**
     * Metodo para settear el numero de partidas
     * @param user nombre del usuario
     * @param num numero de victorias
     */
    @Override
    public void setNumGames(String user, int num){
        conn.connect();
        String query = "UPDATE User SET partides_jugades = '" + num + "' WHERE USERNAME like '"+user+"'";
        conn.UpdateQuery(query);
        conn.disconnect();
    }

    //#nuevo
    /**
     * Metodo para settear el numero de partidas ganadas de un usuario
     * @param user el nombre del usuario
     * @param num el numero de partidas ganadas
     */
    @Override
    public void setNumWins(String user, int num){
        conn.connect();
        String query = "UPDATE User SET partides_guanyades = '" + num + "' WHERE USERNAME like '"+user+"'";
        conn.UpdateQuery(query);
        conn.disconnect();
    }
}
