package presentation.controllers;

import business.LogManager;
import business.NpcManager;
import presentation.views.LogsView;
import business.Log;

import java.util.LinkedList;

/**
 * Esta clase sera util para poder controlar la pantalla de Log's
 */
public class LogController {
    private NpcManager npcManager;
    private LogManager logm;

    /**
     * Este es el constructor de la clase
     * @param npcManager el gestor del npc
     */
    public LogController(NpcManager npcManager) {
        this.npcManager = npcManager;
        this.logm = new LogManager();
    }

    /**
     * Esta clase, permite actualizar los log's
     */
    public void updateLogs() {

        for (int i = 0; i < npcManager.getPlayers().size(); i++) {
            if (npcManager.checkLogPosition(npcManager.getPlayers().get(i))) {
                logm.addLog(npcManager.getPlayers().get(i));
            }
        }
    }

    /**
     * Getter de los logs de la partida
     * @return el listado con los logs
     */
    public LinkedList<Log> getLogs() {
        return logm.getLogs();
    }
}