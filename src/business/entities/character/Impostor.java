package business.entities.character;

import business.MapManager;
import business.NpcManager;
import business.entities.Time;
import business.entities.map.Cell;
import business.entities.map.Mobility;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase representara a los personajes que sean impostores durante la partida.
 * Como podemos ver extiende de la clase de personajes, ya que un impostor no deja de ser un personaje
 */
public class Impostor extends Character{
    private static final int minInterval = 6;
    private static final int maxInterval = 8;
    private int startInterval;
    private final MapManager mapManager;
    private NpcManager npcManager;
    private Boolean canKill;
    private final Time killingPeriod;


    /**
     * Este sera el constructor de los impostores
     * @param color el color del impostor
     * @param mapManager el map manager que asociaremos al impostor
     */
    // Parametrized constructor
    public Impostor(String color, MapManager mapManager) {
        super(color);
        this.mapManager = mapManager;
        killingPeriod = new Time();
    }

    /**
     * Setter de la capacidad de asesinar del personaje
     * @param canKill un booleano que indica si este personaje puede matar a otros personajes
     */
    public void setCanKill(Boolean canKill) {
        this.canKill = canKill;
    }

    /**
     * Setter de el manager de todos los NPC's
     * @param npcManager el NPC Manager que queremos asociar
     */
    @Override
    public void setNpcManager(NpcManager npcManager) {
        this.npcManager = npcManager;
    }

    /**
     * Método que nos dice si el impostor se va a mover o no
     * @return un boolea que nos dice si se mueve o no
     */
    public synchronized boolean movement() {
        int probability = (int)(Math.random()*(getMaxProbability() + 1));
        return probability <= 45;
    }

    /**
     * Este metodo retorna el intervalo de tiempo entre acciones
     * @return El timer que tenemos entre acciones
     */
    public int getInterval() {
        return randomInterval(maxInterval, minInterval);
    }


    /**
     * Método que indica cual sera la parcela a la que se movera el impostor
     * @param impostor El impostor
     * @return La parcela a la que se movera el impostor
     */
    public synchronized int getNextImpostorRoom(Impostor impostor) {
        Mobility mobility = impostor.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int randomPosition = getRandomPosition(counter);
        return chooseRoom(randomPosition);
    }

    /**
     * Método que comprueba si en esta parcela hay ventilación disponible
     * @param cell parcela donde se encuentra el impostor
     * @return booleano que indica si hay ventilación o no
     */
    public synchronized boolean checkVentilation(Cell cell) {
        return !cell.getAlcantarilla().isEmpty();
    }


    /**
     *Método que nos indica que ventilación cojerá para moverse
     * @param cell La parcela donde se encuentra el impostor
     * @return posicion de la parcela donde se movera
     */
    public synchronized int chooseVentilationRoom(Cell cell) {
        return getRandomPosition(cell.getAlcantarilla().size());
    }

    /**
     *Método que hace el 50% de una probabilidad
     * @return booleano que nos dice si o no
     */
    public synchronized boolean flipCoin() {
        return (int) (Math.random() * (2) + 1) == 1;
    }


    /**
     * Método que nos indica que parcela es en función de unas cordenadas
     * @param coordinates Las cordenadas de la parcela que queremos conocer
     * @return la parcela que queremos saber, o null en caso de que no exista
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
     * Método que actualiza la posicion del impostor
     * @param impostor El impostor
     * @throws InterruptedException excepción que indica si el sleep deja de funcionar
     */

    public synchronized void impostorMovement(Impostor impostor) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);

        if (startInterval == getIntervalTime().getSeconds()) {
            if (impostor.movement()) {
                if (!ventilationMovement(impostor)) {
                    int nextRoom = getNextImpostorRoom(impostor);
                    System.out.println("El impostor se mueve");
                    int[] nextCell = impostor.getNextCoordinates(nextRoom);
                    impostor.setCell(getCellByCoordinates(nextCell));
                    impostor.setCanLog(true);

                }
            }
            startInterval = getInterval();
            getIntervalTime().resetCounter();
        }
    }

    /**
     * Método que comprubea si el impostor puede moverse a traves de la ventilación
     * @param impostor El impostor
     * @return Booleano que indica si se puede mover el impostor a traves de la ventilación
     */
    public synchronized boolean ventilationMovement(Impostor impostor) {
        if (checkVentilation(impostor.getCell())) {
            if (npcManager.getNumNpcCell(impostor.getCell()) == 0 && flipCoin()) {
                int nextRoom = chooseVentilationRoom(impostor.getCell());
                String roomName = impostor.getCell().getAlcantarilla().get(nextRoom);
                int numNpcs = npcManager.getNumNpcCell(mapManager.getMap().getCellByName(roomName));
                if (numNpcs == 0 || (numNpcs == 1 && impostor.canKill)) {
                    impostor.setCell(mapManager.getMap().getCellByName(roomName));
                    impostor.setCanLog(true);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Método que mueve al impostor automáticamente después de matar a un jugador
     * @param impostor El impostor
     */
    public void afterKillMovement(Impostor impostor) {
        int nextRoom = getNextImpostorRoom(impostor);
        int[] nextCell = impostor.getNextCoordinates(nextRoom);
        impostor.setCell(getCellByCoordinates(nextCell));
        impostor.setCanLog(true);

    }

    /**
     * Este metodo nos permite recibir cuanto tiempo queda hasta que el impostor pueda volver a matar
     * @return el timer de la cuenta atras de la cantidad de asesinato
     */
    public Time getPeriodTime() {
        return killingPeriod;
    }


    /**
     *Método que comprueba si el impostor puede matar a otro usuario o esta en tiempo de espera
     * @param impostor el impostor
     * @return Booleano que nos indica si puedo o no matar
     */
    public synchronized boolean checkKillingPeriod(Impostor impostor) {
        if (impostor.getPeriodTime().getSeconds() > 25) {
            impostor.setCanKill(true);
            return true;
        }
        return false;
    }


    /**
     *Thread que hace continuamente todas las acciones del impstor
     */
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
                    if (npcManager.eliminateNpc(mapManager, this)) {
                        canKill = false;
                        afterKillMovement(this);
                    }
                }

                Thread.sleep(500);

            } catch (InterruptedException e) {
                System.out.println("La Exception salta pero no afecta al juego");
            }
        }
    }
}
