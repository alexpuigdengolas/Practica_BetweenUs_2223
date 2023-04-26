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

        if(userDAO.gameNameExists(game.getGameName())){
            throw new ErrorMessage("El nom del game ya existeix.");
        } else if (!chechImp(game).equals("Correcte")) {
            throw  new ErrorMessage(chechImp(game));
        }

    }

    public void saveGame(Game game){
        userDAO.saveGame(game);
    }

    private String chechImp(Game game) {
        //si <6 impos = 1 si entre 6 i 8 impos 2 si 9 o 10
        if(game.getPlayers() < 6 && game.getImpostors() > 1){
            return "No pot haber-hi mes de 1 impostor amb "+ game.getPlayers()+" jugadors";
        }else if (game.getPlayers() < 9 && game.getImpostors() > 2) {
            return "No pot haber-hi mes de 2 impostors amb "+ game.getPlayers()+" jugadors";
        }else{
        return "Correcte";
        }
    }
}
