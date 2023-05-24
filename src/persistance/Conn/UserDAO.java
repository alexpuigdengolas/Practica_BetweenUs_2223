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
    //#nuevo
    int getNumVictories(String user);
    //#nuevo
    void setNumGames(String user,int num);
    //#nuevo
    int getNumGames(String user);
    //#nuevo
    void setNumWins(String user, int num);



}
