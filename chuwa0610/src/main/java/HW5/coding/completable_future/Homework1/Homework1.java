package HW5.coding.completable_future.Homework1;

import java.util.concurrent.CompletableFuture;

public class Homework1 {
    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 10;

        // .supplyAsync(): 异步的方式去执行计算任务
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            // 计算和
            return num1 + num2;
        });

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            // 计算积
            return num1 * num2;
        });

        // thenAcceptAsync() 异步等待并获取结果
        sumFuture.thenAcceptAsync(sum -> {
            System.out.println("Sum of " + num1 + " and " + num2 + " is: " + sum);
        });

        productFuture.thenAcceptAsync(product -> {
            System.out.println("Product of " + num1 + " and " + num2 + " is: " + product);
        });

        // 主线程等待所有异步任务完成， allOf()组合两个任务，得到一个新的CompletableFuture<Void>对象，allFutures
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(sumFuture, productFuture);
        allFutures.join(); // 使得主线程等待所有任务完成,然后退出程序
    }
}
