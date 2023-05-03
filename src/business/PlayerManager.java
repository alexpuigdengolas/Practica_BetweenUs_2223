package business;

import business.entities.character.Player;
import business.entities.map.Cell;

public class PlayerManager {

    private final Player player;


    public PlayerManager(Player character) {
        this.player = character;
    }

    public Player getPlayer() {
        return player;
    }


}