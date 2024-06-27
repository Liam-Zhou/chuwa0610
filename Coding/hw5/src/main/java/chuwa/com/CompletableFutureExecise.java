package chuwa.com;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExecise {


    public static void main(String[] args) {
        int a = 5;
        int b = 3;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> computeSum(a, b));
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> computeProduct(a, b));

        sumFuture.thenAcceptAsync(sum -> {
            System.out.println("Sum of " + a + " and " + b + " is: " + sum);
        });

        productFuture.thenAcceptAsync(product -> {
            System.out.println("Product of " + a + " and " + b + " is: " + product);
        });

        // Adding a delay to see asynchronous execution
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int computeSum(int a, int b) {
        return a + b;
    }

    private static int computeProduct(int a, int b) {
        return a * b;
    }

}
