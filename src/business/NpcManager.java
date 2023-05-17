package business;

import business.entities.character.Character;
import business.entities.character.Impostor;
import business.entities.character.Npc;
import business.entities.map.Cell;

import java.util.LinkedList;

public class NpcManager{
    private final LinkedList<Character> players;

    // Parametrized constructor
    public NpcManager(LinkedList<Character> players) {
        this.players = players;
    }

    public LinkedList<Character> getPlayers() {
        return players;
    }

    public void interruptThreads(){
        for (Character character: players) {
            character.stopThread();
        }
    }

    public int getNumCrewMembersCell(Cell cell) {
        int crewMembers = 0;
        for (Character character: players) {
            if (character.getCell() == cell && character instanceof Npc) {
                if (!character.isDead()) {
                    crewMembers++;
                }
            }
        }
        return crewMembers;
    }

    public boolean eliminateUserPlayer(Character userPlayer) {
        if (getNpcNumCell(userPlayer.getCell()) == 1) {
            int npcListPosition = getNpcPosition(userPlayer.getCell());
            if (players.get(npcListPosition) instanceof Impostor) {
                Impostor impostor = (Impostor) players.get(npcListPosition);
                if (impostor.checkKillingPeriod(impostor)) {
                    return true;
                }
                return impostor.isCanKill();
            }
        }
        return false;
    }
    public int getNpcNumCell(Cell cell) {
        int numNpc = 0;
        for (Character character: players) {
            if (character.getCell() == cell) {
                numNpc++;
            }
        }
        return numNpc;
    }
    public int getNpcPosition(Cell cell) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCell() == cell) {
                return i;
            }
        }
        return 0;
    }
    public synchronized boolean eliminateCrewMember(MapManager mapManager, Impostor impostor) {
        if (crewMemberKilled(mapManager,impostor)) {
            return true;
        }
        return false;
    }

    public boolean crewMemberKilled(MapManager mapManager, Impostor impostor) {
        if (getNpcNumCell(impostor.getCell()) == 2 && getNumCrewMembersCell(impostor.getCell()) == 1
                && mapManager.userPlayerCell() != impostor.getCell()) {
            int crewMemberPosition = getNpcPosition(impostor.getCell());
            if (!players.get(crewMemberPosition).isDead() && crewMemberPosition != -1) {
                int cellPosition = getCellPosition(mapManager, impostor.getCell());
                mapManager.getMap().getCells().get(cellPosition).setNumCorpses(mapManager.getMap().getCells().get(cellPosition).getNumCorpses() + 1);
                players.get(crewMemberPosition).setDead(true);
                players.get(crewMemberPosition).stopThread();
                System.out.println("El impostot a matat a "+players.get(crewMemberPosition).getColor());

                impostor.getPeriodTime().resetCounter();

                return true;
            }
        }
        return false;
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
