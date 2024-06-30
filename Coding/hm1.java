package com.chuwa.exercise.collection.hw5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class hm1 {
    public static void main(String[] args) {
        int a = 2;
        int b = 7;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // Combine both futures to print the results
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sumFuture, productFuture);

        // When both futures are done, print the results
        combinedFuture.thenRun(() -> {
            try {
                int sum = sumFuture.get();
                int product = productFuture.get();
                System.out.println("Sum: " + sum);
                System.out.println("Product: " + product);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        combinedFuture.join();
    }
}
