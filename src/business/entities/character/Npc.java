package business.entities.character;

import business.MapManager;
import business.entities.Time;
import business.entities.map.Cell;
import business.entities.map.Mobility;

public class Npc extends Character{
    private int previousRoom;
    private static final int minInterval = 5;
    private static final int maxInterval = 15;
    private MapManager mapManager;
    private int startInterval;
    public Npc(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
    }

    // Parametrized constructor
    public synchronized boolean movement() {
        int probability = (int)(Math.random()*(getMaxProbability() + 1));
        return probability <= 55;
    }

    public int getInterval() {
        return randomInterval(maxInterval, minInterval);
    }

    @Override
    public Cell getCell() {
        return super.getCell();
    }

    @Override
    public void setCell(Cell cell) {
        super.setCell(cell);
    }

    public int getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(int previousRoom) {
        this.previousRoom = previousRoom;
    }


    public synchronized int selectPreviousRoom(int nextRoom) {
        switch (nextRoom) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            case 3:
                return 1;
        }
        return -1;
    }

    /**
     * Mètode que mou al crewmember si aquest decideix moure's
     */
    public synchronized void npcMovement() {
        if (startInterval == getIntervalTime().getSeconds()) {
            if (movement()) {
                int nextRoom = getNextCrewMemberRoom(this);
                setPreviousRoom(selectPreviousRoom(nextRoom));
                int[] nextCell = getNextCoordinates(nextRoom);
                setCell(getCellByCoordinates(nextCell));

            }
            startInterval = getInterval();
            getIntervalTime().resetCounter();
        }
    }


    public synchronized Cell getCellByCoordinates(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        for (Cell cell: mapManager.getMap().getCells()) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
    }


    public synchronized int getCrewMemberRandomPosition(int counter, int previousRoom) {
        int position = (int) (Math.random() * (counter));
        if (Math.abs(position-previousRoom) == 2) {
            return (int) (Math.random() * (counter));
        }
        return position;
    }



    public synchronized int getNextCrewMemberRoom(Npc npc) {
        Mobility mobility = npc.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int randomPosition = getCrewMemberRandomPosition(counter, npc.getPreviousRoom());
        return chooseRoom(randomPosition);
    }


    @Override
    public void run() {
        getTotalTime().initCounter();
        getIntervalTime().initCounter();
        startInterval = getInterval();
        while (isRunning()) {
            try {
                npcMovement();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
