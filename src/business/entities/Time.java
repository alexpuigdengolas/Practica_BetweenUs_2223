package business.entities;

import java.util.Timer;
import java.util.TimerTask;

public class Time {
    // Attributes
    private Timer timer = new Timer();
    private int seconds = 0;

    private class Counter extends TimerTask {
        public synchronized void run() {
            seconds++;
        }
    }


    public void initCounter() {
        this.seconds = 0;
        timer = new Timer();
        timer.schedule(new Counter(), 0, 1000);
    }


    public void resetCounter() {
        seconds = 0;
    }


    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}