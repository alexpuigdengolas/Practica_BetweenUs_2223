package business;

import business.entities.Game;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

import java.util.ArrayList;

public class DeleteManager {

    private UserDAO userDAO;
    public DeleteManager() {
        this.userDAO = new UserSQLDAO();
    }

    public ArrayList<String> getGames(){
        ArrayList<String> games;
        games = userDAO.getGames();
        return games;
    }

    public void deleteGame(String game){
        userDAO.deleteGame(game);
    }
}
