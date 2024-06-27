package com.chuwa.learn.multithread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class c25 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 5;
        int b = 6;
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(()->{
            return a+b;
        });

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        CompletableFuture<Void> combineFuture = CompletableFuture.allOf(sumFuture, productFuture);

        combineFuture.thenRun(() -> {
            try {
                System.out.println("Sum: " + sumFuture.get());
                System.out.println("Product: "+ productFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).get();
    }
}
