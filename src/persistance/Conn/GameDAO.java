package persistance.Conn;

import business.entities.Game;

import java.util.ArrayList;

public interface GameDAO {

    void saveGame(Game game);
    ArrayList<String> getGames();
    Game searchGame(String game);
    void deleteGame(String game);
    void deleteGames(String user);
    ArrayList<Float> searchGameStatistics(String user);
    void setGameStatistic(String user, int game, float percentage);

}
