package com.chuwa.learn.multithread.hw5.c01_create;


import java.util.concurrent.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Thread t = new Thread();
        t.start();  // create new thread
        // t.run() // run task in current process/thread
        System.out.println("***********");

        // implements Runnable
        Thread t2 = new Thread(new MyRunnable());
        t2.start();

        Thread t3 = new Thread(() -> System.out.println("from lambda"));
        t3.start();

        // user implements Callable
        Callable<String> callable  = new MyCallable();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<String>> list = new ArrayList<>();
        Future<String> future = executorService.submit(callable);
        list.add(future);
        Future<String>  stringFuture = list.get(0);

        for(int i = 0; i < 5; ++i) {
            Thread.sleep(1000);
            String s = future.get();
            System.out.println(s);
        }
        executorService.shutdown();
    }
}
