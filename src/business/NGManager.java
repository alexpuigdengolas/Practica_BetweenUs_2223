package business;


import business.entities.Game;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

public class NGManager {
    private UserDAO userDAO;
    public NGManager() {
        this.userDAO = new UserSQLDAO();
    }

    public void checkGame(Game game) throws ErrorMessage{
        //TODO: que el tema de los impostores cuadre.
        if(userDAO.gameNameExists(game.getGameName())){
            throw new ErrorMessage("El nom del game ya existeix.");
        } else if (!chechImp(game)) {
            throw  new ErrorMessage("Els impostors no son correctes.");
        }

    }

    public void saveGame(Game game){
        userDAO.saveGame(game);
    }

    private boolean chechImp(Game game) {
        //TODO: mirar requisitos de los impostores.
        return true;
    }
}
