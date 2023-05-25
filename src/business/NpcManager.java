package business;

import business.entities.character.Character;
import business.entities.character.Impostor;
import business.entities.character.Npc;
import business.entities.map.Cell;

import java.util.LinkedList;

/**
 * Esta clase servira como gestor de los NPC que no pueden matar a otros personajes
 */
public class NpcManager{
    private final LinkedList<Character> players;

    /**
     * Este sera el constructor de nuestra clase
     * @param players los personajes que queremos controlar en este gestor
     */
    // Parametrized constructor
    public NpcManager(LinkedList<Character> players) {
        this.players = players;
    }

    /**
     * Getter de los personajes
     * @return los personajes de esta clase
     */
    public LinkedList<Character> getPlayers() {
        return players;
    }

    /**
     * Este metodo permitira interumpir todos los threads relacionados con los NPC's
     */
    public void interruptThreads(){
        for (Character character: players) {
            character.stopThread();
        }
    }

    /**
     *
     * @param cell
     * @return
     */
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

    /**
     *
     * @param cell
     * @return
     */
    public int getNpcNumCell(Cell cell) {
        int numNpc = 0;
        for (Character character: players) {
            if (character.getCell() == cell) {
                numNpc++;
            }
        }
        return numNpc;
    }

    /**
     * Getter de la posicion de los NPC's
     * @param cell las casillas en las que se encuentran los NPC's
     * @return
     */
    public int getNpcPosition(Cell cell) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCell() == cell && players.get(i) instanceof Npc) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Metodo para poder eliminar a un NPC
     * @param mapManager el gestor de mapas
     * @param impostor el impostor que ha asesinado al NPC
     * @return un booleano que indica si se pudo asesinar al NPC o no
     */
    public synchronized boolean eliminateNpc(MapManager mapManager, Impostor impostor) {
        return npcKilled(mapManager, impostor);
    }

    /**
     * Este metodo nos servira para poder matar a un NPC
     * @param mapManager el gestor de mapas
     * @param impostor el impostor que ha asesinado al NPC
     * @return un booleano que indica si se pudo asesinar al NPC o no
     */
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

    /**
     *
     * @param mapManager
     * @param cell
     * @return
     */
    public int getCellPosition (MapManager mapManager, Cell cell) {
        for (int i = 0; i < mapManager.getMap().getCells().size(); i++) {
            if (mapManager.getMap().getCells().get(i) == cell) {
                return i;
            }
        }
        return -1;
    }

    //#nuevo
    /**
     * Getter del numero de NPC's vivos
     * @return un entero con el numero de NPC's vivos
     */
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
    /**
     * Getter del numero de impostores activos
     * @return el numero de impostores
     */
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
    /**
     * Metodo para poder conseguir logs
     * @param character el personaje del cual cual queremos checkear su log para añadirlo mas tarde al listado
     * @return un booleano que indica si se envio el log
     */
    public boolean checkLogPosition (Character character) {
        return checkLog(character);
    }

    /**
     * Metodo para mostrar por consola el log
     * @param character el personaje que queremos comprobar su log
     * @return un booleano que indica si se envio el log
     */
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
