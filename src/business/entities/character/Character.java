package business.entities.character;


import business.NpcManager;
import business.entities.map.Cell;
import business.entities.map.Mobility;
import business.entities.Time;



public abstract class Character extends Thread {

    private Time totalTime =  new Time();
    private Time intervalTime = new Time();
    private final int[] moveOptions = new int[4];
    private String color;
    private Cell cell;
    private int xCoordinate;
    private int yCoordinate;
    private boolean isRunning;
    private boolean isDead;
    private boolean canLog;




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
    public synchronized void startThread() {
        isRunning = true;
        this.start();
    }

    public synchronized void stopThread() {
        isRunning = false;
        this.interrupt();
    }
    public int getMaxProbability() {
        return 100;
    }
    public synchronized int randomInterval(int maxInterval, int minInterval) {
        return (int)(Math.random()*(maxInterval - minInterval + 1) + minInterval);
    }

    public void setNpcManager(NpcManager npcManager){}
    public synchronized boolean isRunning() {
        return isRunning;
    }

    public Time getIntervalTime() {
        return intervalTime;
    }
    public Time getTotalTime() {
        return totalTime;
    }
    public synchronized boolean checkLeft(Mobility mobility) {
        return mobility.getLeft() != 0;
    }


    public synchronized boolean checkRight(Mobility mobility) {
        return mobility.getRight() != 0;
    }


    public synchronized boolean checkUp(Mobility mobility) {
        return mobility.getUp() != 0;
    }


    public synchronized boolean checkDown(Mobility mobility) {
        return mobility.getDown() != 0;
    }
    public synchronized int setMoveOptions(Mobility mobility) {
        int counter = 0;
        if (checkLeft(mobility)) {
            moveOptions[0] = 1;
            counter++;
        } else {
            moveOptions[0] = 0;
        }
        if (checkUp(mobility)) {
            moveOptions[1] = 1;
            counter++;
        } else {
            moveOptions[1] = 0;
        }
        if (checkRight(mobility)) {
            moveOptions[2] = 1;
            counter++;
        } else {
            moveOptions[2] = 0;
        }
        if (checkDown(mobility)) {
            moveOptions[3] = 1;
            counter++;
        } else {
            moveOptions[3] = 0;
        }
        return counter;
    }
    public synchronized int chooseRoom(int randomPosition) {
        int optionsCounter = -1;
        for (int i = 0; i < 4; i++) {
            if (moveOptions[i] == 1) {
                optionsCounter++;
                if (optionsCounter == randomPosition) {
                    return i;
                }
            }
        }
        return 0;
    }
}
