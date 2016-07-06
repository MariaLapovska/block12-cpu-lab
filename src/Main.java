/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        ProcessFlow processFlow = new ProcessFlow();

        processFlow.run();

        System.out.println(processFlow.calculateUsedCPUs());
        System.out.println(processFlow.calculatePercentage());
    }
}