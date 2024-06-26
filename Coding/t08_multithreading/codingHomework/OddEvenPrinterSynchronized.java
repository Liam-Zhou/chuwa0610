package com.chuwa.exercise.t08_multithreading.codingHomework;




// Using synchronized and wait/notify
public class OddEvenPrinterSynchronized {
    private final Object lock = new Object();
    private boolean oddTurn = true;

    public static void main(String[] args) {
        OddEvenPrinterSynchronized printer = new OddEvenPrinterSynchronized();
        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);
        oddThread.start();
        evenThread.start();
    }

    public void printOdd() {
        synchronized (lock) {
            for (int i = 1; i <= 9; i += 2) {
                while (!oddTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = false;
                lock.notify();
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            for (int i = 2; i <= 10; i += 2) {
                while (oddTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = true;
                lock.notify();
            }
        }
    }
}
