package business;

import business.entities.character.Character;

import java.util.LinkedList;

/**
 * Este metodo servira para poder gestionar los logs
 */
public class LogManager {
    // Attributes
    //private LogDAO logDAO;
    private String gameName;
    private LinkedList<Log> logs;

    // Parametrized constructor

    /**
     * Constructor de la clase
     */
    public LogManager() {
        logs = new LinkedList<>();
    }

    /**
     * Mètode que afegeix un log a la llista
     * @param character per poder agafar les dades i guardar-les
     */
    public void addLog(Character character) {
        Log log = new Log(character.getColor(), character.getCell().getRoomName(), character.getTotalTime().getSeconds());
        logs.add(log);
    }

    /**
     * Getter de los logs
     * @return el listado de Log's que se generan en la partida
     */
    public LinkedList<Log> getLogs() {
        return logs;
    }
}
