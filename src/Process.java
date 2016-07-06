/**
 * Process class
 */
public class Process {

    /** Time interval for the next process generation */
    private int time;
    private int ID;
    private static volatile int nextID = 0;

    /**
     * Defines the upper and lower bounds for random generator of the
     * ID corresponds to the current value of static incremented  nextID
     * @param min Lower boundary
     * @param max Upper boundary
     */
    public Process(int min, int max) {
        time = (int) (min + Math.random() * (max - min + 1)) * 10;
        ID = nextID++;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Process{" +
                "ID=" + ID +
                '}';
    }
}