package presentation.controllers;

import business.LogManager;
import business.NpcManager;
import presentation.views.LogsView;
import presentation.views.custom.Log;

import java.util.LinkedList;

public class LogController {
    private NpcManager npcManager;
    private LogsView logv;
    private LogManager logm;

    public LogController(NpcManager npcManager, LogsView logv, String gameName) {
        this.npcManager = npcManager;
        this.logv = logv;
        this.logm = new LogManager(gameName);
    }

    public void updateLogs() {

        for (int i = 0; i < npcManager.getPlayers().size(); i++) {
            if (npcManager.checkLogPosition(npcManager.getPlayers().get(i))) {
                logm.addLog(npcManager.getPlayers().get(i));
            }
        }
    }

    public LinkedList<Log> getLogs() {
        return logm.getLogs();
    }
}