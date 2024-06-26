package com.chuwa.exercise.t08_multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.*;

public class hw5_15 {
    public static void main(String[] args) {
        // Step 1: Create a ThreadPool
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Step 2: Submit a Runnable Task
        Runnable runnableTask = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Runnable Task Completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        executorService.execute(runnableTask);

        // Step 3: Submit a Callable Task
        Callable<String> callableTask = () -> {
            TimeUnit.SECONDS.sleep(2);
            return "Callable Task Result";
        };
        Future<String> future = executorService.submit(callableTask);

        try {
            // Step 4: Retrieve the Result from the Callable Task
            String result = future.get();
            System.out.println("Callable Task Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Step 5: Shutdown the ThreadPool
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}

