package business;

import persistance.Conn.GameDAO;
import persistance.Conn.GameSQLDAO;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

import java.util.ArrayList;

/**
 * Esta clase ejerce como gestor de la estadisticas
 */
public class StatisticsManager {

    private UserDAO userDAO;
    private GameDAO gameDAO;

    /**
     * Este es el constructor de la clase
     */
    public StatisticsManager(){
        this.userDAO = new UserSQLDAO();
        this.gameDAO = new GameSQLDAO();
    }

    /**
     * Este es el buscador de las estadisticas
     * @param user el usuario del cual queremos sacar las estadisticas
     * @return
     */
    public ArrayList<Float> searchGameStatistics(String user){
        return gameDAO.searchGameStatistics(user);
    }

    /**
     * Setter de las estadisticas en la base de datos
     * @param user el ususario
     * @param game el nombre de la partida
     * @param percentage el porcentaje despues de esta partida
     */
    public void setGameStatistic(String user, String game, float percentage){
        gameDAO.setGameStatistic(user, game, percentage);
    }

}
