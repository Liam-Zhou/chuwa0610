package com.chuwa.learn;

import java.util.concurrent.CompletableFuture;

public class Q25a {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });
        sumFuture.thenAccept((sum)->{
            System.out.println("Sum: " + sum);
        });
        productFuture.thenAccept((product) -> {
            System.out.println("Product: " + product);
        });

    }

}
