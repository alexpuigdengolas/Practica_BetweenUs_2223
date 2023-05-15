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


}
