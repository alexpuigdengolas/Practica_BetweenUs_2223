package business;

import business.entities.character.Character;
import business.entities.character.Npc;
import business.entities.map.Cell;

import java.util.LinkedList;

public class NpcManager{
    private final LinkedList<Npc> players;

    // Parametrized constructor
    public NpcManager(LinkedList<Npc> players) {
        this.players = players;
    }

    public LinkedList<Npc> getPlayers() {
        return players;
    }
    public void interruptThreads(){
        for (Character character: players) {
            character.stopThread();
        }
    }

    /**
     * Mètode que retorna el nombre de crewmembers
     * @return nombre de crewmemebers
     */
    public int getNumCrewMembers() {
        int numCrewMembers = 0;
        for (Character character: players) {
            if (character instanceof Npc) {
                numCrewMembers++;
            }
        }
        return numCrewMembers;
    }

    public int getNpcPosition(Cell cell) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCell() == cell) {
                return i;
            }
        }
        return 0;
    }


    public int getCrewMemberPosition(Cell cell) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCell() == cell && players.get(i) instanceof Npc) {
                return i;
            }
        }
        return -1;
    }


    public int getCellPosition (MapManager mapManager, Cell cell) {
        for (int i = 0; i < mapManager.getMap().getCells().size(); i++) {
            if (mapManager.getMap().getCells().get(i) == cell) {
                return i;
            }
        }
        return -1;
    }

}
