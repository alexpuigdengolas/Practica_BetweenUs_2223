package business;

import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

import java.util.ArrayList;

public class StatisticsManager {

    private UserDAO userDAO;

    public StatisticsManager(){
        this.userDAO = new UserSQLDAO();
    }

    public ArrayList<Float> searchGameStatistics(String user){
        return userDAO.searchGameStatistics(user);
    }

    public void setGameStatistic(String user, int game, float percentage){
        userDAO.setGameStatistic(user, game, percentage);
    }
}
