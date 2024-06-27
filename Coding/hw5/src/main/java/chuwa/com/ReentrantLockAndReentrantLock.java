package chuwa.com;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockAndReentrantLock {

    private static final Lock lock = new ReentrantLock();
    private static final Condition oddCondition = lock.newCondition();
    private static final Condition evenCondition = lock.newCondition();
    private static int count = 1;
    private static final int MAX_COUNT = 10;
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter(), "Thread-0");
        Thread evenThread = new Thread(new EvenPrinter(), "Thread-1");

        oddThread.start();
        evenThread.start();
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            while (count <= MAX_COUNT) {
                lock.lock();
                try {
                    if (isOddTurn) {
                        System.out.println(Thread.currentThread().getName() + ": " + count);
                        count++;
                        isOddTurn = false;
                        evenCondition.signal();
                    } else {
                        try {
                            oddCondition.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            while (count <= MAX_COUNT) {
                lock.lock();
                try {
                    if (!isOddTurn) {
                        System.out.println(Thread.currentThread().getName() + ": " + count);
                        count++;
                        isOddTurn = true;
                        oddCondition.signal();
                    } else {
                        try {
                            evenCondition.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
