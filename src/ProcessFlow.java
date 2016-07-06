import java.util.ArrayList;
import java.util.List;

/**
 * Process flow class
 */
public class ProcessFlow implements Runnable {

    private static final int PROCESS_NUMBER = 10;
    private List<CPU> processors = new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();

    @Override
    public void run() {
        System.out.println("Process flow started");

        for (int i = 0; i < PROCESS_NUMBER; i++) {
            int rand = generateRandomNumber(1, 10);
            Process process = new Process(rand, 2 * rand);
            System.out.println(Thread.currentThread() + "\t" + process + " generated");

            try {
                System.out.println(Thread.currentThread() + "\tTime: " + process.getTime() + "\n");
                Thread.sleep(process.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean found = false;

            for (CPU processor : processors) {
                if (!processor.isBusy()) {
                    processor.setProcess(process);
                    found = true;

                    break;
                }
            }

            if (!found) {
                CPU processor = new CPU();
                processor.setProcess(process);
                processors.add(processor);

                Thread thread = new Thread(processor);
                threads.add(thread);
                thread.start();
            }
        }

        threads.forEach(Thread::interrupt);

        System.out.println("Process flow finished");
    }

    public String calculateUsedCPUs() {
        return "Number of used CPUs: " + processors.size();
    }

    public String calculatePercentage() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = processors.size();

        for (CPU cpu : processors) {
            stringBuilder.append(cpu.toString() + " load: " +
                    ((double) cpu.getWorkedProcesses()) / ((double) PROCESS_NUMBER) * 100 + "%\n");
        }

        return stringBuilder.toString();
    }

    private int generateRandomNumber(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }
}
