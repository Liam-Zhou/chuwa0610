public class PrintNumber1 {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> printNumbersInRange(1, 10, "Thread-0: "));
        Thread thread2 = new Thread(() -> printNumbersInRange(11, 20, "Thread-2: "));
        Thread thread3 = new Thread(() -> printNumbersInRange(21, 30, "Thread-1: "));

        // Start threads in a random order
        startInRandomOrder(thread1, thread2, thread3);
    }

    private static void startInRandomOrder(Thread... threads) {
        // Shuffle the threads array to start them in random order
        Thread[] shuffledThreads = shuffleArray(threads);
        
        // Start threads in the shuffled order
        for (Thread thread : shuffledThreads) {
            thread.start();
        }
    }

    private static void printNumbersInRange(int start, int end, String threadNamePrefix) {
        synchronized (lock) {
            for (int i = start; i <= end; i++) {
                System.out.println(threadNamePrefix + i);
                try {
                    Thread.sleep(50); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Thread[] shuffleArray(Thread[] array) {
        // Fisher-Yates shuffle algorithm
        for (int i = array.length - 1; i > 0; i--) {
            int index = (int) (Math.random() * (i + 1));
            Thread temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
}