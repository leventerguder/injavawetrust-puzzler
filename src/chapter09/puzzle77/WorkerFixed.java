package chapter09.puzzle77;

import java.util.Timer;
import java.util.TimerTask;

public class WorkerFixed extends Thread {

    private final Object lock = new Object();

    private volatile boolean quittingTime = false;

    public void run() {
        while (!quittingTime)
            pretendToWork();
        System.out.println("Beer is good");
    }

    private void pretendToWork() {
        try {
            Thread.sleep(300); // Sleeping on the job?
        } catch (InterruptedException ex) {
        }
    }

    // It's quitting time, wait for worker - Called by good boss
    synchronized void quit() throws InterruptedException {
        synchronized (lock) {
            quittingTime = true;
            join();
        }
    }

    // Rescind quitting time - Called by evil boss
    synchronized void keepWorking() {
        synchronized (lock) {
            quittingTime = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final WorkerFixed worker = new WorkerFixed();
        worker.start();
        Timer t = new Timer(true); // Daemon thread
        t.schedule(new TimerTask() {
            public void run() {
                worker.keepWorking();
            }
        }, 500);
        Thread.sleep(400);
        worker.quit();
    }
}
