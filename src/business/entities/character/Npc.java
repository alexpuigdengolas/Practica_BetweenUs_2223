package business.entities.character;

import business.MapManager;
import business.entities.map.Cell;
import business.entities.map.Mobility;

/**
 * Esta clase sirve para representar todos los personajes que no sean controlados
 * por el usuario y que no son capaces de asesinar a otros personajes
 */
public class Npc extends Character{
    private int previousRoom;
    private static final int minInterval = 5;
    private static final int maxInterval = 15;
    private final MapManager mapManager;
    private int startInterval;

    /**
     * Este es el constructor de la clase NPC
     * @param color el color de el personaje
     * @param mapManager el map manager asociado al personaje
     */
    public Npc(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
    }

    /**
     * Metodo que controla el movimiento del personaje
     * @return Un booleano que indica si el personaje se puede mover en este momento o no
     */
    public synchronized boolean movement() {
        int probability = (int)(Math.random()*(getMaxProbability() + 1));
        System.out.println("La probabilidad ha salido: "+probability);
        return probability <= 55;
    }

    /**
     * Getter del intervalo maximo de accion del personaje
     * @return el intervalo maximo
     */
    public int getInterval() {
        return randomInterval(maxInterval, minInterval);
    }

    /**
     * Getter de la casilla en la que se encuetra el personaje
     * @return la casilla en la que se encuentra el personaje
     */
    @Override
    public Cell getCell() {
        return super.getCell();
    }

    /**
     * Setter de la casilla de la casilla de personajes
     * @param cell la casilla en la que se encuentra
     */
    @Override
    public void setCell(Cell cell) {
        super.setCell(cell);
    }

    /**
     * Getter de la habitacion anterior en la que se encontraba el personaje
     * @return el entero que representa la habitación anterior en la que se encontraba el personaje
     */
    public int getPreviousRoom() {
        return previousRoom;
    }

    /**
     * Setter de la habitacion anterior en la que se encontraba el personaje
     * @param previousRoom el entero que representa la habitación anterior en la que se encontraba el personaje
     */
    public void setPreviousRoom(int previousRoom) {
        this.previousRoom = previousRoom;
    }

    /**
     * Metodo para seleccionar la habitacion anterior en la que se encontraba el personaje
     * @param nextRoom la habitación siguiente a la que accedera el personaje
     * @return el entero de la habitacion anterior
     */
    public synchronized int selectPreviousRoom(int nextRoom) {
        return switch (nextRoom) {
            case 0 -> 2;
            case 1 -> 3;
            case 2 -> 0;
            case 3 -> 1;
            default -> -1;
        };
    }

    /**
     * Este metodo controlara el movimiento de los personajes
     */
    public synchronized void npcMovement() {


        if (startInterval == getIntervalTime().getSeconds()) {
            if (movement()) {
                System.out.println("la previus room es: "+getPreviousRoom());
                System.out.println("soy la ficha color "+getColor()+" y me muevo");
                int nextRoom = getNextNpcRoom(this);
                System.out.println("La next room es: "+nextRoom);
                setPreviousRoom(selectPreviousRoom(nextRoom));
                int[] nextCell = getNextCoordinates(nextRoom);
                System.out.println("se va a la celda:  "+getCellByCoordinates(nextCell).getRoomName());
                setCell(getCellByCoordinates(nextCell));
                setCanLog(true);

            }
            startInterval = getInterval();
            getIntervalTime().resetCounter();
        }
    }

    /**
     * Este metodo retornara una casilla introduciendo sus coordenadas
     * @param coordinates las coordenadas de la casilla
     * @return la casilla en si
     */
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

    /**
     * Getter de una posicion random para el NPC
     * @param counter contador del tiempo en el que se van a dar estas posiciones
     * @param previousRoom la habitación anterior del personaje
     * @return el int de una posicion random para el jugador
     */
    public synchronized int getNpcRandomPosition(int counter, int previousRoom) {
        System.out.println("counter es: "+counter);
        int position = (int) (Math.random() * (counter));
        System.out.println("position es: "+position);
        System.out.println("La previus room es: "+previousRoom);
        if (Math.abs(position-previousRoom) == 2) {
            return (int) (Math.random() * (counter));
        }
        return position;
    }


    /**
     * Este metodo retornara la siguiente habitacion a la que se va a desplazar el presonaje
     * @param npc el personaje que se ecuentra en la siguiente habitacion
     * @return el indicador de la habitacion
     */
    public synchronized int getNextNpcRoom(Npc npc) {
        Mobility mobility = npc.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int randomPosition = getNpcRandomPosition(counter, npc.getPreviousRoom());
        return chooseRoom(randomPosition);
    }


    /**
     * Este metodo lanzara el Thread the este personaje para que pueda actuar como se
     * especifica en el enunciado
     */
    @Override
    public void run() {

        getTotalTime().initCounter();
        getIntervalTime().initCounter();
        startInterval = getInterval();
        try {
            while (isRunning()) {

                npcMovement();

                Thread.sleep(500);

            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
