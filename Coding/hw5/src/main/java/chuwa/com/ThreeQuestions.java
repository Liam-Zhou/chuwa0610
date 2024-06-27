package chuwa.com;

public class ThreeQuestions {
    public static void main(String[] args) {
        // Create and start the threads
        Thread thread1 = new Thread(new NumberPrinter(1, 10), "Thread-0");
        Thread thread2 = new Thread(new NumberPrinter(11, 20), "Thread-2");
        Thread thread3 = new Thread(new NumberPrinter(21, 30), "Thread-1");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    // Runnable class to print a range of numbers
    static class NumberPrinter implements Runnable {
        private final int start;
        private final int end;

        public NumberPrinter(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    // Sleep to simulate work and make the output interleaved
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }

}
