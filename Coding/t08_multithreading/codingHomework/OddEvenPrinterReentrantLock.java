package com.chuwa.exercise.t08_multithreading.codingHomework;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class OddEvenPrinterReentrantLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean oddTurn = true;

    public static void main(String[] args) {
        OddEvenPrinterReentrantLock printer = new OddEvenPrinterReentrantLock();
        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);
        oddThread.start();
        evenThread.start();
    }

    public void printOdd() {
        lock.lock();
        try {
            for (int i = 1; i <= 9; i += 2) {
                while (!oddTurn) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = false;
                condition.signal();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void printEven() {
        lock.lock();
        try {
            for (int i = 2; i <= 10; i += 2) {
                while (oddTurn) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
                oddTurn = true;
                condition.signal();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
