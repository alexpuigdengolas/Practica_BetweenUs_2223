package business;

import business.entities.Game;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;
import presentation.views.ChargeView;

import java.util.ArrayList;


//TODO: Borrar aquest manager y pasarlo al Game Manger
public class ChargeManager {

    private ChargeView chargeView;

    private UserDAO userDAO;

    ArrayList<String> games = new ArrayList<>();

    public ChargeManager(ChargeView chargeView) {
        this.userDAO = new UserSQLDAO();
        this.chargeView = chargeView;
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
    public void updateCharge(){
        games = getGames();
        chargeView.updateComboBoxList(games);
    }
}
