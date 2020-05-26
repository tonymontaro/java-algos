package Tools;

public class MeasureExecutionTime {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        // run some code

        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}
