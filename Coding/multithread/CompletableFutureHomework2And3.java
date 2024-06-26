import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureHomework2And3 {

    public static void main(String[] args) {
        CompletableFuture<String> productsFuture = fetchDataWithExceptionHandling("https://jsonplaceholder.typicode.com/posts");
        CompletableFuture<String> reviewsFuture = fetchDataWithExceptionHandling("https://jsonplaceholder.typicode.com/comments");
        CompletableFuture<String> inventoryFuture = fetchDataWithExceptionHandling("https://jsonplaceholder.typicode.com/users");

        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allOfFuture.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();
                System.out.println("Products: " + products);
                System.out.println("Reviews: " + reviews);
                System.out.println("Inventory: " + inventory);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).join();
    }

    private static CompletableFuture<String> fetchDataWithExceptionHandling(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    return reader.lines().collect(Collectors.joining("\n"));
                }
            } catch (Exception e) {
                System.err.println("Failed to fetch data from: " + url + ". Returning default value.");
                e.printStackTrace();
                return "[]";
            }
        });
    }
}