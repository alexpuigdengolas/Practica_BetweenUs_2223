import Conn.Data;
import Conn.JsonReader;

public class Main {
    private static Data data;
    public static void main(String[] args) {

            data = JsonReader.llegeixJSON();
        System.out.println(data.getUser());

    }
}