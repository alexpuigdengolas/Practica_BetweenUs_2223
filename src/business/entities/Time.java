package business.entities;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase representara los temporizadores de las partidas
 */
public class Time {
    // Attributes
    private Timer timer = new Timer();
    private int seconds = 0;

    /**
     * Esta sera la clase que emplearemos como contador para poder tener nuestro timer
     */
    private class Counter extends TimerTask {
        public synchronized void run() {
            seconds++;
        }
    }

    /**
     * Metodo para inicializar el contador
     */
    public void initCounter() {
        this.seconds = 0;
        timer = new Timer();
        timer.schedule(new Counter(), 0, 1000);
    }

    /**
     * Metodo para resetear el contador
     */
    public void resetCounter() {
        seconds = 0;
    }

    /**
     * Getter de los segundos del contador
     * @return los segundos del contador
     */
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * Setter de los segundos del contador
     * @param seconds los segundos
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    //#nuevo
    public void stopTimer(){
        this.timer.cancel();
    }
}