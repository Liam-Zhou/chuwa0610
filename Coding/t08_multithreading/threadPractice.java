package com.chuwa.exercise.t08_multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class threadPractice {
    public static void main(String[] args) throws InterruptedException {
        // entend thread class
        MyThread myThread = new MyThread(1);
        MyThread myThread2 = new MyThread(2);
        MyThread myThread3 = new MyThread(12);

        myThread.start();
        myThread2.start();
        myThread3.start();



        //implement runnable method
        for (int i = 0; i < 2; i++) {
            MyThreadRunnable runnable = new MyThreadRunnable("apple");
            Thread thread = new Thread(runnable);
            thread.start();

        }

        myThread.join();
    }


}

