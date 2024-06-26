package com.chuwa.exercise.t08_multithreading.codingHomework;

import java.util.concurrent.CompletableFuture;

public class SumProduct_1 {
    public static void main(String[] args) {
        int num1 = 50;
        int num2 = 100;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> num1 + num2);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> num1 * num2);

        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));

        // To keep the main thread alive until all tasks are completed
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}
