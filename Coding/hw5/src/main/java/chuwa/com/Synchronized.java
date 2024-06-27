package chuwa.com;

public class Synchronized {
    private static final Object lock = new Object();
    private static int count = 1;
    private static final int MAX_COUNT = 10;

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter(), "Thread-0");
        Thread evenThread = new Thread(new EvenPrinter(), "Thread-1");

        oddThread.start();
        evenThread.start();
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (count <= MAX_COUNT) {
                    if (count % 2 != 0) { // Odd number
                        System.out.println(Thread.currentThread().getName() + ": " + count);
                        count++;
                        lock.notify(); // Notify waiting thread (EvenPrinter)
                    } else {
                        try {
                            lock.wait(); // Wait for notification from EvenPrinter
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (count <= MAX_COUNT) {
                    if (count % 2 == 0) { // Even number
                        System.out.println(Thread.currentThread().getName() + ": " + count);
                        count++;
                        lock.notify(); // Notify waiting thread (OddPrinter)
                    } else {
                        try {
                            lock.wait(); // Wait for notification from OddPrinter
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

}
