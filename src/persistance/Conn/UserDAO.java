package persistance.Conn;

import business.entities.Game;
import business.entities.User;

import java.util.ArrayList;

public interface UserDAO {
    void registerUser(User user);
    boolean checkLoginUser(String userNameMail, String password);
    void deleteUser(String username);
    boolean userNameExists(String userName);
    boolean userMailExists(String userMail);
    String getUsername(String loginName);
    boolean gameNameExists(String gameName);
    void saveGame(Game game);
    ArrayList<String> getGames();
    Game searchGame(String game);
    void deleteGame(String game);
    void deleteGames(String user);
    ArrayList<Float> searchGameStatistics(String user);
    void setGameStatistic(String user, int game, float percentage);
    //#nuevo
    int getNumGames(String user);
    //#nuevo
    int getNumVictories(String user);

    //#nuevo
    void setNumGames(String user);

}
