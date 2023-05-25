package persistance.Conn;

/**
 * Esta clase reoresentara la data para poder acceder a la base de datos
 */
public class Data {
    private int port;
    private String user;
    private String password;
    private String db;
    private String serverIP;
    private int listeningPort;

    /**
     * Este es el constructor vacio de la clase de data
     */
    public Data(){}

    /**
     * Getter del puerto de la data
     * @return el puerto
     */
    public int getPort() {
        return port;
    }

    /**
     * Getter del nombre de usuario de acceso a la base de datos
     * @return el nombre de usuario
     */
    public String getUser() {
        return user;
    }

    /**
     * Getter de la contraseña de la base de datos
     * @return la contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * La base de datos a la que debemos acceder
     * @return la base de datos
     */
    public String getDb() {
        return db;
    }
}
