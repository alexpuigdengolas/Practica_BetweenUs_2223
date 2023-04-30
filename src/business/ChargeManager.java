package business;

import business.entities.Game;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

import java.util.ArrayList;

public class ChargeManager {
    private UserDAO userDAO;
    public ChargeManager() {
        this.userDAO = new UserSQLDAO();
    }

    public ArrayList<String> getGames(){
        ArrayList<String> games;
        games = userDAO.getGames();
        return games;
    }
    public Game searchGame(String game){
        Game joc = userDAO.searchGame(game);
        return joc;
    }
}
