package business;

import business.entities.character.Player;
import business.entities.map.Cell;

public class PlayerManager {
    // Attributes
    private Player player;

    // Parametrized constructor
    public PlayerManager(Player player) {
        this.player = player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void moveUserPlayer(Cell cell) {
        getPlayer().setCell(cell);
    }

    public int[] nextCell(int nextRoom) {
        return getPlayer().getNextCoordinates(nextRoom);
    }

    public boolean checkLeft() {
        return getPlayer().getCell().getMobility().getLeft() != 0;
    }
    public boolean checkRight() {
        return getPlayer().getCell().getMobility().getRight() != 0;
    }
    public boolean checkUp() {
        return getPlayer().getCell().getMobility().getUp() != 0;
    }
    public boolean checkDown() {
        return getPlayer().getCell().getMobility().getDown() != 0;
    }

}