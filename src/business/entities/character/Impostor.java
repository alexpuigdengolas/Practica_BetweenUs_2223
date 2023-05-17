package business.entities.character;

import business.MapManager;
import business.NpcManager;
import business.entities.Time;
import business.entities.map.Cell;
import business.entities.map.Mobility;

import java.util.concurrent.TimeUnit;

public class Impostor extends Character{
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private int startInterval;
    private MapManager mapManager;
    private NpcManager npcManager;
    private Boolean canKill;
    private Time killingPeriod;


    // Parametrized constructor
    public Impostor(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
        killingPeriod = new Time();
    }

    public Boolean isCanKill() {
        return canKill;
    }

    public void setCanKill(Boolean canKill) {
        this.canKill = canKill;
    }

    @Override
    public void setNpcManager(NpcManager npcManager) {
        this.npcManager = npcManager;
    }


    public synchronized boolean movement() {
        int probability = (int)(Math.random()*(getMaxProbability() + 1));
        return probability <= 45;
    }

    public int getInterval() {
        return randomInterval(maxInterval, minInterval);
    }


    public synchronized int getNextImpostorRoom(Impostor impostor) {
        Mobility mobility = impostor.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int randomPosition = getRandomPosition(counter);
        return chooseRoom(randomPosition);
    }

    public synchronized boolean checkVentilation(Cell cell) {
        return !cell.getAlcantarilla().isEmpty();
    }


    public synchronized int chooseVentilationRoom(Cell cell) {
        return getRandomPosition(cell.getAlcantarilla().size());
    }

    public synchronized boolean flipCoin() {
        return (int) (Math.random() * (2) + 1) == 1;
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


    public synchronized void impostorMovement(Impostor impostor) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);

        if (startInterval == getIntervalTime().getSeconds()) {
            if (impostor.movement()) {
                if (!ventilationMovement(impostor)) {
                    int nextRoom = getNextImpostorRoom(impostor);
                    System.out.println("El impostor se mueve");
                    int[] nextCell = impostor.getNextCoordinates(nextRoom);
                    impostor.setCell(getCellByCoordinates(nextCell));
                }
            } else {
                System.out.println("");
            }
            startInterval = getInterval();
            getIntervalTime().resetCounter();
        }
    }

    public synchronized boolean ventilationMovement(Impostor impostor) {
        if (checkVentilation(impostor.getCell())) {
            if (npcManager.getNumCrewMembersCell(impostor.getCell()) == 0 && flipCoin()) {
                int nextRoom = chooseVentilationRoom(impostor.getCell());
                String roomName = impostor.getCell().getAlcantarilla().get(nextRoom);
                int numCrewMembers = npcManager.getNumCrewMembersCell(mapManager.getMap().getCellByName(roomName));
                if (numCrewMembers == 0 || (numCrewMembers == 1 && impostor.canKill)) {
                    impostor.setCell(mapManager.getMap().getCellByName(roomName));
                    return true;
                }
            }
        }
        return false;
    }


    public void afterKillMovement(Impostor impostor) {
        int nextRoom = getNextImpostorRoom(impostor);
        int[] nextCell = impostor.getNextCoordinates(nextRoom);
        impostor.setCell(getCellByCoordinates(nextCell));

    }

    public Time getPeriodTime() {
        return killingPeriod;
    }



    public synchronized boolean checkKillingPeriod(Impostor impostor) {
        if (impostor.getPeriodTime().getSeconds() > 25) {
            impostor.setCanKill(true);
            return true;
        }
        return false;
    }


    @Override
    public void run() {
        getTotalTime().initCounter();
        getIntervalTime().initCounter();
        startInterval = getInterval();
        killingPeriod.initCounter();
        killingPeriod.setSeconds(26);
        while (isRunning()) {
            try {
                impostorMovement(this);
                if (checkKillingPeriod(this) || canKill == null) {
                    if (npcManager.eliminateCrewMember(mapManager, this)) {
                        canKill = false;
                        afterKillMovement(this);
                    }
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Parametrized constructor

}
