package com.chuwa.learn.future;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();


        Callable<String> task = () -> {

            Thread.sleep(5000);

            return "Task completed";
        };


        Future<String> future = executorService.submit(task);

        System.out.println("Task submitted, waiting for result...");

        try {

            String result = future.get(2, TimeUnit.SECONDS);
            System.out.println("Result from task: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }


        executorService.shutdown();
    }
}