package business.entities.character;


import business.NpcManager;
import business.entities.map.Cell;
import business.entities.map.Mobility;
import business.entities.Time;

/**
 * Es una Clase abstracta donde guardamos los datos generales de los distintos personajes del juego
 *
 * En esta clase podemos obtener un codigo mucho mas limpio y ordenado gracias a el concepto de herencia.
 *
 */

public abstract class Character extends Thread {

    private Time totalTime =  new Time();
    private Time intervalTime = new Time();
    private final int[] moveOptions = new int[4];
    private String color;
    private Cell cell;
    private boolean isRunning;
    private boolean isDead;
    private boolean canLog;

    /**
     * Este sera el constructor de nuestros personajes donde setearemos el color y su estado (Muerto o vivo)
     * @param color el color que queremos que tenga el personaje
     */
    // Parametrized constructor
    public Character(String color) {
        this.color = color;
        isDead = false;
        canLog = false;
    }

    /**
     * Getter de la casilla en la que se encuentra el personaje
     * @return la casilla y toda su información
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Getter del color del personaje
     * @return el nombre del color
     */
    public String getColor() {
        return color;
    }

    /**
     * Setter del color del personaje
     * @param color el nombre del color asociado al personaje
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Setter de la casilla en la que se encuentra el ususario
     * @param cell la casilla en la que se encuentra
     */
    public void setCell(Cell cell) {this.cell = cell;}

    /**
     * Getter del estado de log del personaje
     * @return un booleano que indica si el personaje puede hacer log
     */
    public boolean isCanLog() {
        return canLog;
    }

    /**
     * Setter del estado de log del personaje
     * @param canLog un booleano que indica si el personaje puede hacer log
     */
    public void setCanLog(boolean canLog) {
        this.canLog = canLog;
    }

    /**
     * Getter de la probabilidad maxima de movimiento
     * @return un entero que represente la probabilidad maxima
     */
    public int getMaxProbability() {
        return 100;
    }

    /**
     * Setter del NPC Manager asociado al personaje
     * @param npcManager el NPC Manager que queremos asociar
     */
    public void setNpcManager(NpcManager npcManager){}

    /**
     * Este metodo nos servira para poder comprobar si el thread del personaje esta activo
     * @return booleano que indica esta información
     */
    public synchronized boolean isRunning() {
        return isRunning;
    }

    /**
     * Este metodo retorna el intervalo de tiempo entre acciones
     * @return El timer que tenemos entre acciones
     */
    public Time getIntervalTime() {
        return intervalTime;
    }

    /**
     * Este metodo retorna el temporizador de existencia del personaje
     * @return cuanto tiempo lleva existiendo el personaje
     */
    public Time getTotalTime() {
        return totalTime;
    }

    /**
     *Metodo que calcula las cordenadas de la sala donde se dirige el jugador
     * @param nextRoom sala donde va el jugador
     * @return Cordenadas de la sala donde va
     */
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

    /**
     *Metodo que inicia el thread de los diferentes personajes
     */
    public synchronized void startThread() {
        isRunning = true;
        this.start();
    }

    /**
     * Metodo que para el thread de los diferentes personajes
     */
    public synchronized void stopThread() {
        isRunning = false;
        this.interrupt();
    }


    /**
     *Metodo que nos devuelve el intervalo que tardara el jugador en intentar moverse
     * @param maxInterval número máximo de tiempo de espera
     * @param minInterval número mínimo de tiempo de espera
     * @return numero final que va a esperar
     */
    public synchronized int randomInterval(int maxInterval, int minInterval) {
        return (int)(Math.random()*(maxInterval - minInterval + 1) + minInterval);
    }


    /**
     * Metodo que comprueba si el usuario puede moverse hacia la izquierda
     * @param mobility la movilidad del jugador en una posicion específica
     * @return booleano que nos dice si puede moverse o no
     */
    public synchronized boolean checkLeft(Mobility mobility) {
        return mobility.getLeft() != 0;
    }

    /**
     * Metodo que comprueba si el usuario puede moverse hacia la derecha
     * @param mobility la movilidad del jugador en una posicion específica
     * @return booleano que nos dice si puede moverse o no
     */
    public synchronized boolean checkRight(Mobility mobility) {
        return mobility.getRight() != 0;
    }

    /**
     * Metodo que comprueba si el usuario puede moverse hacia arriba
     * @param mobility la movilidad del jugador en una posicion específica
     * @return booleano que nos dice si puede moverse o no
     */
    public synchronized boolean checkUp(Mobility mobility) {
        return mobility.getUp() != 0;
    }

    /**
     * Metodo que comprueba si el usuario puede moverse hacia abajo
     * @param mobility la movilidad del jugador en una posicion específica
     * @return booleano que nos dice si puede moverse o no
     */
    public synchronized boolean checkDown(Mobility mobility) {
        return mobility.getDown() != 0;
    }

    /**
     * Método que calcula hacia cuantas salas se puede mover el jugador
     * @param mobility La mobilidad del jugador
     * @return integer del número de salas a las que se va a poder mover
     */
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

    /**
     * Metodo que elije la habitación donde ira el jugador
     * @param randomPosition sala donde va a ir el jugador de forma aletoria
     * @return devuelve un entero indicando la dirreción del movimiento
     */
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

    /**
     * Metodo que nos da un numero aleatorio entre los posibles movimientos del jugador
     * @param counter Numero de sala a las que puede acceder el jugador (1-4)
     * @return nos devuelve un int con la dirección del jugador
     */
    public int getRandomPosition(int counter) {
        return (int) (Math.random() * (counter));
    }

    /**
     * Este metodo nos retorna si el personaje esta muerto
     * @return un booleano que indica si esta muerto
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Este metodo seteara que el personaje este muerto o no
     * @param dead booleano del estado queremos que tenga el personaje
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }
}
