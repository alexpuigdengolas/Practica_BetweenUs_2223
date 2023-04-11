package persistance.Conn;


public class Data {
    private int port;
    private String user;
    private String password;
    private String db;
    private String serverIP;
    private int listeningPort;

    public Data(){}

    public int getPort() {
        return port;
    }
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
    public String getDb() {
        return db;
    }
}
