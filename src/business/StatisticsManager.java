package business;

import persistance.Conn.GameDAO;
import persistance.Conn.GameSQLDAO;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

import java.util.ArrayList;

public class StatisticsManager {

    private UserDAO userDAO;
    private GameDAO gameDAO;

    public StatisticsManager(){
        this.userDAO = new UserSQLDAO();
        this.gameDAO = new GameSQLDAO();
    }

    public ArrayList<Float> searchGameStatistics(String user){
        return gameDAO.searchGameStatistics(user);
    }

    public void setGameStatistic(String user, int game, float percentage){
        gameDAO.setGameStatistic(user, game, percentage);
    }




}
