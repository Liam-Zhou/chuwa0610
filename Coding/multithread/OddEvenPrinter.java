public class OddEvenPrinter {
    private static final Object lock = new Object();
    private static int number = 1;
    private static final int MAX_COUNT = 10;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (number <= MAX_COUNT) {
                synchronized (lock) {
                    if (number % 2 != 0) {
                        System.out.println("Thread-0: " + number++);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            while (number <= MAX_COUNT) {
                synchronized (lock) {
                    if (number % 2 == 0) {
                        System.out.println("Thread-1: " + number++);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}