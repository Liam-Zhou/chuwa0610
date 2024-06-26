package multithreading;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * description: CompletableFutureLearn
 * date: 6/26/24 3:50 PM
 * author: jinhao_pang
 * version: 1.0
 */
public class CompletableFutureLearn {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 2, b = 3;
        CompletableFuture<int[]> completableFuture = CompletableFuture.supplyAsync(() -> new int[]{a + b, a * b});
        System.out.println(Arrays.toString(completableFuture.get()));
    }
}
