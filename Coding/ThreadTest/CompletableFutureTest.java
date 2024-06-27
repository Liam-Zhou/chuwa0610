package ThreadTest;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public int sum(int a, int b) {
        return a + b;
    }

    public int product(int a, int b) {
        return a * b;
    }


    public static void main(String[] args) {
        CompletableFutureTest completableFutureTest = new CompletableFutureTest();
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(()->completableFutureTest.sum(3,5));
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(()->completableFutureTest.product(3,5));
        try {
            Integer sum = sumFuture.get();
            Integer product = productFuture.get();
            System.out.println(sum);
            System.out.println(product);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
