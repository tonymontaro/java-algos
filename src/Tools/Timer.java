package Tools;

public class Timer {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        // run some code

        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }


    static class timer {
        public long startTime;
        public void start() {
            startTime = System.nanoTime();
        }
        public void stop() {
            long timeElapsed = System.nanoTime() - startTime;
            System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        }
    }
}
