import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class FetchData {
    private static String products;
    private static String reviews;
    private static String inventory;
    private static CompletableFuture<String> fetchData(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    return reader.lines().collect(Collectors.joining("\n"));
                }
            } catch (Exception e) {
                System.err.println("Failed");
                return "[]";
            }
        });
    }

    public static void fetchDatas(String productUrl, String reviewsUrl, String inventoryUrl) {
        CompletableFuture<String> productsFuture = fetchData(productUrl);
        CompletableFuture<String> reviewsFuture = fetchData(reviewsUrl);
        CompletableFuture<String> inventoryFuture = fetchData(inventoryUrl);
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allOfFuture.thenRun(() -> {
            try {
                products = productsFuture.get();
                reviews = reviewsFuture.get();
                inventory = inventoryFuture.get();
            } catch (Exception e) {
                System.err.println("Failed");
            }
        }).join();
    }

    public static String getMerged() {
        return products+reviews+inventory;
    }

    public static void main(String[] args) {
        FetchData.fetchDatas("https://jsonplaceholder.typicode.com/posts",
                "https://jsonplaceholder.typicode.com/comments",
                "https://jsonplaceholder.typicode.com/users");
        System.out.println(FetchData.getMerged());
    }

}