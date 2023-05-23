package persistance.Conn;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnector {
    private static String userName;
    private static String password;
    private static String db;
    private static int port;
    private static String url = "jdbc:mysql://localhost";
    private static Connection conn = null;
    private static Statement s;

    public DBConnector(String usr, String pass, String db, int port) {
        DBConnector.userName = usr;
        DBConnector.password = pass;
        DBConnector.db = db;
        DBConnector.port = port;
        DBConnector.url += ":" + port + "/";
        DBConnector.url += db;
        DBConnector.url += "?verifyServerCertificate=false&useSSL=false";
    }
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
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
        }
    }
    public void insertQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
    public void deleteQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdateQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}