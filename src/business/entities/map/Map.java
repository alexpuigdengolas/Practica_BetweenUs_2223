package business.entities.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

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
    private final LinkedList<Cell> cells = null;

    public int getCellNumber() {
        return cellNumber;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getMapName() {
        return mapName;
    }
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
}