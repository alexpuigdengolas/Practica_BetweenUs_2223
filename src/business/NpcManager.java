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

    public int getNumNpcCell(Cell cell) {
        int npcs = 0;
        for (Character character: players) {
            if (character.getCell() == cell && character instanceof Npc) {
                if (!character.isDead()) {
                    npcs++;
                }
            }
        }
        return npcs;
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
            if (players.get(i).getCell() == cell && players.get(i) instanceof Npc) {
                return i;
            }
        }
        return 0;
    }
    public synchronized boolean eliminateNpc(MapManager mapManager, Impostor impostor) {
        return npcKilled(mapManager, impostor);
    }

    public boolean npcKilled(MapManager mapManager, Impostor impostor) {
        if (getNpcNumCell(impostor.getCell()) == 2 && getNumNpcCell(impostor.getCell()) == 1
                && mapManager.userPlayerCell() != impostor.getCell()) {
            int npcPosition = getNpcPosition(impostor.getCell());
            System.out.println("Quiero matar a alguien");
            for (Character player : players) {
                System.out.println(player.getColor());
            }
            System.out.println("Quiero matar a :"+players.get(npcPosition).getColor());
            //SE mata a si mismo
            if (!players.get(npcPosition).isDead() && npcPosition != -1 && players.get(npcPosition) instanceof Npc) {
                int cellPosition = getCellPosition(mapManager, impostor.getCell());
                mapManager.getMap().getCells().get(cellPosition).setNumCorpses(mapManager.getMap().getCells().get(cellPosition).getNumCorpses() + 1);
                players.get(npcPosition).setDead(true);
                players.get(npcPosition).stopThread();
                System.out.println("El impostot a matat a "+players.get(npcPosition).getColor());

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

    //#nuevo
    public int getNumNpc() {
        int numNpcs = 0;
        for (Character character: players) {
            if (character instanceof Npc && !character.isDead()) {
                numNpcs++;
            }
        }
        return numNpcs;
    }

    //#nuevo
    public int getNumImpostors() {
        int numImpostors = 0;
        for (Character character: players) {
            if (character instanceof Impostor) {
                numImpostors++;
            }
        }
        return numImpostors;
    }

    //#nuevo
    //mriar de borrar este metodo
    public boolean checkLogPosition (Character character) {
        return checkLog(character);
    }


    public boolean checkLog(Character character) {
        if (!character.getCell().getRoomName().equals("corridor") &&
                !character.getCell().getRoomName().equals("security") &&
                !character.isDead() && character.isCanLog()) {
            System.out.println(" el color "+ character.getColor()+ " deberia meterse en el log");
            character.setCanLog(false);
            return true;
        }
        return false;
    }

}
