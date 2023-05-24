package business;

import business.entities.character.Player;
import business.entities.map.Cell;

/**
 * Esta clase representa el gestor del jugador
 */
public class PlayerManager {
    // Attributes
    private Player player;

    /**
     * El constructor de la clase
     * @param player el jugador a gestionar
     */
    // Parametrized constructor
    public PlayerManager(Player player) {
        this.player = player;
    }

    /**
     * Setter del jugador
     * @param player el jugador en si
     */
    public void setPlayer(Player player){
        this.player = player;
    }

    /**
     * Getter del jugador
     * @return el jugador en si
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Este metodo gestiona el movimiento del jugador
     * @param cell la casilla en la que se encuentra el jugador
     */
    public void moveUserPlayer(Cell cell) {
        getPlayer().setCell(cell);
    }

    /**
     * Gestor para el siguiente movimiento del jugador
     * @param nextRoom la siguiente habitacion disponible para el jugador
     * @return array con las posibles listas de ususarios
     */
    public int[] nextCell(int nextRoom) {
        return getPlayer().getNextCoordinates(nextRoom);
    }

    /**
     * Metodo para comprobar si se puede mover a la izquierda
     * @return un booleano que da la respuesta
     */
    public boolean checkLeft() {
        return getPlayer().getCell().getMobility().getLeft() != 0;
    }

    /**
     * Metodo para comprobar si se puede mover a la derecha
     * @return un booleano que da la respuesta
     */
    public boolean checkRight() {
        return getPlayer().getCell().getMobility().getRight() != 0;
    }

    /**
     * Metodo para comprobar si se puede mover a la arriba
     * @return un booleano que da la respuesta
     */
    public boolean checkUp() {
        return getPlayer().getCell().getMobility().getUp() != 0;
    }

    /**
     * Metodo para comprobar si se puede mover a la abajo
     * @return un booleano que da la respuesta
     */
    public boolean checkDown() {
        return getPlayer().getCell().getMobility().getDown() != 0;
    }

}