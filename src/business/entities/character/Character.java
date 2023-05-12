package business.entities.character;


import business.entities.map.Cell;

public abstract class Character {

    private final int[] moveOptions = new int[4];
    private String color;
    private Cell cell;
    private int xCoordinate;
    private int yCoordinate;
    private boolean isRunning;
    private boolean isDead;
    private boolean canLog;

    public Character (String color, int xCoordinate, int yCoordinate) {
        this.color = color;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        isDead = false;
    }

    // Parametrized constructor
    public Character(String color) {
        this.color = color;
        isDead = false;
    }
    public Cell getCell() {
        return cell;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public void setCell(Cell cell) {this.cell = cell;}

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public synchronized int[] getNextCoordinates(int nextRoom) {
        int[] actualRoom = new int[2];
        actualRoom[0] = getCell().getX();
        actualRoom[1] = getCell().getY();
        switch (nextRoom) {
            case 0:
                actualRoom[0] -= 1;
                return actualRoom;
            case 1:
                actualRoom[1] -= 1;
                return actualRoom;
            case 2:
                actualRoom[0] += 1;
                return actualRoom;
            case 3:
                actualRoom[1] += 1;
                return actualRoom;
            default:
                actualRoom[0] = -1;
                actualRoom[1] = -1;
                return actualRoom;
        }
    }
}
