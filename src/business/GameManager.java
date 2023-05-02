package business;


import business.entities.Game;
import business.entities.map.Cell;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

import java.util.LinkedList;

public class GameManager {
    private UserDAO userDAO;
    public GameManager() {
        this.userDAO = new UserSQLDAO();
    }


    //Comprueba que el juego tenga las cosas correctas y envia una excepcion si falla alguna cosa
    public void checkGame(Game game) throws ErrorMessage{

        if(userDAO.gameNameExists(game.getGameName())){
            throw new ErrorMessage("El nom del game ya existeix.");
        } else if (!chechImp(game).equals("Correcte")) {
            throw  new ErrorMessage(chechImp(game));
        } else if(game.getGameName().isEmpty() || game.getGameName().equals(" ")){
            throw new ErrorMessage("El nom del joc no pot estar buit");
        }

    }

    //Llama la base de datos para guardar los datos del juego
    public void saveGame(Game game){
        userDAO.saveGame(game);
    }


    //Comprueba las diferentes condiciones de los impostores y nos devuelve el mensaje de error o correcte segun esta bien o mal
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
    public Cell getCafeCell(LinkedList<Cell> cells) {
        for (Cell cell: cells) {
            if (cell.getRoomName().equals("cafeteria")) {
                return cell;
            }
        }
        return null;
    }



}
