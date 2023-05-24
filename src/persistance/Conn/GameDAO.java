package persistance.Conn;

import business.entities.Game;

import java.util.ArrayList;

public interface GameDAO {

    void saveGame(Game game);
    ArrayList<String> getGames(String user);
    Game searchGame(String game);
    void deleteGame(String game);
    void deleteGames(String user);
    ArrayList<Float> searchGameStatistics(String user);
    void setGameStatistic(String user, String game, float percentage);
    boolean gameNameExists(String gameName);

}
