package persistance.Conn;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta clase nos sera util para poder conectar con la base de datos
 */
public class DBConnector {
    private static String userName;
    private static String password;
    private static String db;
    private static int port;
    private static String url = "jdbc:mysql://localhost";
    private static Connection conn = null;
    private static Statement s;

    /**
     * Este es el constructor de la clase
     * @param usr el nombre de usuario de la base de datos
     * @param pass la contraseña de acceso a la base de datos
     * @param db el nombre de la base de datos
     * @param port el puerto de acceso de la base de datos
     */
    public DBConnector(String usr, String pass, String db, int port) {
        DBConnector.userName = usr;
        DBConnector.password = pass;
        DBConnector.db = db;
        DBConnector.port = port;
        DBConnector.url += ":" + port + "/";
        DBConnector.url += db;
        DBConnector.url += "?verifyServerCertificate=false&useSSL=false";
    }

    /**
     * Este metodo nos permite conectarnos a la base de datos
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("Conexió a base de dades " + url + " ... Ok");
            }
        } catch (SQLException ex) {
            System.out.println("Problema al connectar-nos a la BBDD --> " + url);
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe no trobada");
        }
    }

    /**
     * Este metodo nos permite deconectarnos de la base de datos
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
        }
    }

    /**
     * Este metodo nos permte hacer queries (de tipo insert) con la base de datos
     * @param query la query que queremos hacer
     */
    public void insertQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo nos permte hacer queries (de tipo select) con la base de datos
     * @param query la query que queremos hacer
     * @return el resultado de la query
     */
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            s = conn.createStatement();
            rs = s.executeQuery (query);
        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
        return rs;
    }

    /**
     * Este metodo nos permte hacer queries (de tipo delete) con la base de datos
     * @param query la query que queremos hacer
     */
    public void deleteQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo nos permte hacer queries (de tipo update) con la base de datos
     * @param query la query que queremos hacer
     */
    public void UpdateQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}