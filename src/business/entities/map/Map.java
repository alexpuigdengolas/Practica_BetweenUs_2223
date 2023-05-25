package business.entities.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

/**
 * Esta clase servira para representar el mapa
 */
public class Map {

    @SerializedName("cellNumber")
    @Expose
    private int cellNumber;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("mapName")
    @Expose
    private String mapName;
    @SerializedName("cells")
    @Expose
    private final LinkedList<Cell> cells = new LinkedList<>();

    /**
     * Getter del numero de casillas que componen el mapa
     * @return el numero de casillas
     */
    public int getCellNumber() {
        return cellNumber;
    }

    /**
     * Este metodo nos retorna el ancho del mapa
     * @return el valor del ancho del mapa
     */
    public int getWidth() {
        return width;
    }

    /**
     * Este metodo nos retorna el alto del mapa
     * @return el valor del alto del mapa
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter del nombre del mapa
     * @return el nombre del mapa
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Getter de las casillas que componen el mapa
     * @return las casillas
     */
    public LinkedList<Cell> getCells() {
        return cells;
    }
    public Cell getCellByCoordinates(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        for (Cell cell: this.cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Getter de una casilla por su nombre especifico
     * @param roomName el nombre de la casilla que buscamos
     * @return la casilla en si
     */
    public Cell getCellByName(String roomName) {
        for (Cell cell : cells) {
            if (cell.getRoomName().equals(roomName)) {
                return cell;
            }
        }
        System.out.println("Cell null: "+ roomName);
        return null;
    }
}