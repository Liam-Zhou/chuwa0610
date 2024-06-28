import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEventPrinterReentrant {
    private static final Lock lock = new ReentrantLock();
    private static final Condition oddCondition = lock.newCondition();
    private static final Condition evenCondition = lock.newCondition();
    private static int number = 1;
    private static final int MAX_COUNT = 10;
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (number <= MAX_COUNT) {
                lock.lock();
                try {
                    while (!isOddTurn) oddCondition.await();
                    if (number <= MAX_COUNT) {
                        System.out.println("Thread-0: " + number++);
                        isOddTurn = false;
                        evenCondition.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            while (number <= MAX_COUNT) {
                lock.lock();
                try {
                    while (isOddTurn) evenCondition.await();
                    if (number <= MAX_COUNT) {
                        System.out.println("Thread-1: " + number++);
                        isOddTurn = true;
                        oddCondition.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}