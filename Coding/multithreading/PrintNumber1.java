package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumber1 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Thread-0: " + i);
                }
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            for (int i = 11; i <= 20; i++) {
                System.out.println("Thread-2: " + i);
            }
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            for (int i = 21; i <= 30; i++) {
                System.out.println("Thread-3: " + i);
            }
            reentrantLock.unlock();
        }).start();
    }
}