package persistance.Conn;

import business.entities.Game;
import business.entities.User;

public interface UserDAO {
    void registerUser(User user);
    boolean checkLoginUser(String userNameMail, String password);
    void deleteUser(String username);
    boolean userNameExists(String userName);
    boolean userMailExists(String userMail);
    String getUsername(String loginName);
    boolean gameNameExists(String gameName);
    void saveGame(Game game);
}
