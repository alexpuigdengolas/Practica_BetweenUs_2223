package business;

import business.entities.Game;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;
import presentation.views.DeleteView;

import java.util.ArrayList;

//TODO: Borrar aquest manager y pasarlo al Game Manger
public class DeleteManager {

    private UserDAO userDAO;

    private DeleteView deleteView;

    public DeleteManager(DeleteView deleteView) {
        this.deleteView = deleteView;
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

    public void updateDelete(){
        ArrayList<String> games;
        games = getGames();
        deleteView.updateComboBoxList(games);
    }
}
