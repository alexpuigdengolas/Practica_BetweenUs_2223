package business;

import business.entities.character.Character;
import presentation.views.custom.Log;

import java.util.LinkedList;

public class LogManager {
    // Attributes
    //private LogDAO logDAO;
    private String gameName;
    private LinkedList<Log> logs;

    // Parametrized constructor
    public LogManager(String gameName) {
        this.gameName = gameName;
        logs = new LinkedList<>();
    }

    /**
     * Mètode que afegeix un log a la llista
     * @param character per poder agafar les dades i guardar-les
     */
    public void addLog(Character character) {
        Log log = new Log(character.getColor(), character.getCell().getRoomName(), character.getTotalTime().getSeconds());
        //logDAO.saveLog(log, gameName);
        logs.add(log);
    }

    public LinkedList<Log> getLogs() {
        return logs;
    }
}
