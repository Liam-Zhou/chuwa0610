import java.util.concurrent.CompletableFuture;

public class AsynchSumAndProduct {

    public static void main(String[] args) {
        int a = 2;
        int b = 3;

        CompletableFuture future0 = CompletableFuture.supplyAsync(() -> a + b).thenAccept(res -> System.out.println("Sum: " + res));
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> a * b).thenAccept(res -> System.out.println("Product: " + res));
        CompletableFuture.allOf(future0, future1).join();
    }
}