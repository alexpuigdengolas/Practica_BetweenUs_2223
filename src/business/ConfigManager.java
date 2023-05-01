package business;

import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;
import presentation.views.ChargeView;
import presentation.views.ConfiguredView;

import java.util.ArrayList;

public class ConfigManager {

    private UserDAO userDAO;

    private ConfiguredView configuredView;

    ArrayList<String> games = new ArrayList<>();
    public ConfigManager(ConfiguredView configuredView) {
        this.configuredView = configuredView;
        this.userDAO = new UserSQLDAO();
    }

    public ArrayList<String> getGames(){
        ArrayList<String> games;
        games = userDAO.getGames();
        return games;
    }

    public void updateConfig(){
        games = getGames();
        configuredView.updateComboBoxList(games);
    }

}
