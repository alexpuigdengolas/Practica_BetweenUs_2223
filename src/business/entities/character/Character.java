package business.entities.character;

import business.entities.map.Cell;

public abstract class Character {
    private String color;
    private int xCoordinate;
    private int yCoordinate;
    private Cell cell;

    public Character (String color, int xCoordinate, int yCoordinate) {
        this.color = color;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

    }
    public Character(String color){
        this.color = color;
    }
    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {this.cell = cell;}

}
