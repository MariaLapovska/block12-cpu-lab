/**
 * Class that emulates work of CPU
 */
public class CPU implements Runnable {

    private int ID;
    private Process process;
    private boolean busy = false;
    private int workedProcesses = 0;
    private static int nextID = 0;

    public CPU() {
        this.ID = nextID++;
    }

    public boolean isBusy() {
        return busy;
    }

    public synchronized void setProcess(Process process) {
        this.process = process;
        workedProcesses++;
        notify();
    }

    public int getWorkedProcesses() {
        return workedProcesses;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                if (process != null) {
                    int operationTime = generateRandTime(1, 5);

                    System.out.println(Thread.currentThread().getName() + " " + toString() + " handle for "
                                        + operationTime + " ms " + process + "\n");
                    busy = true;
                    Thread.sleep(operationTime);
                    busy = false;
                    process = null;
                } else {
                    synchronized (this) {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "CPU{" +
                "ID='" + ID + '\'' +
                '}';
    }

    private int generateRandTime(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1)) * 100;
    }
}