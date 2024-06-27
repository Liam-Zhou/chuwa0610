package com.chuwa.learn.multithread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class c05_23 {

    private static final Object lock = new Object();
    private static int value = 1;
    private static void synchronizedPrint() {

        Runnable runnable = () -> {

            synchronized (lock) {
                while(value <= 10) {
                    System.out.println(Thread.currentThread().getName() + " : " + value++);
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    private static final Lock lockRe = new ReentrantLock();
    private static final Condition conditionRe = lockRe.newCondition();
    private int count = 0;
    private static void ReLock() {
        Runnable runnable = () -> {
            lockRe.lock();
            try {
                while(value <= 10) {
                    System.out.println(Thread.currentThread().getName() + " : " + value++);
                    conditionRe.signal();
                    try{
                        conditionRe.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            } finally {
                lockRe.unlock();
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();


    }
    public static void main(String[] args) {
//        synchronizedPrint();

        ReLock();


    }
}
