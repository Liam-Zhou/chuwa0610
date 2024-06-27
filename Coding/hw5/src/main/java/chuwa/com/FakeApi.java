package chuwa.com;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeApi.class);
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        CompletableFuture<String> productsFuture = fetchData("https://jsonplaceholder.typicode.com/users")
                .exceptionally(ex -> {
                    LOGGER.error("Error fetching products", ex);
                    return "Default Product data";
                });

        CompletableFuture<String> reviewsFuture = fetchData("https://jsonplaceholder.typicode.com/users")
                .exceptionally(ex -> {
                    LOGGER.error("Error fetching reviews", ex);
                    return "Default Review data";
                });

        CompletableFuture<String> inventoryFuture = fetchData("https://jsonplaceholder.typicode.com/users")
                .exceptionally(ex -> {
                    LOGGER.error("Error fetching inventory", ex);
                    return "Default Inventory data";
                });

        // Combine all futures
        CompletableFuture<Void> allOf = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        // Process the results once all futures are complete
        CompletableFuture<MergedData> mergedDataFuture = allOf.thenApply(v -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();
                return mergeData(products, reviews, inventory);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // Print the merged data
        mergedDataFuture.thenAccept(mergedData -> {
            System.out.println("Merged Data:");
            System.out.println("Products: " + mergedData.getProducts());
            System.out.println("Reviews: " + mergedData.getReviews());
            System.out.println("Inventory: " + mergedData.getInventory());
        }).join(); // wait for the completion
    }

    private static CompletableFuture<String> fetchData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    private static MergedData mergeData(String products, String reviews, String inventory) {
        return new MergedData(products, reviews, inventory);
    }

    // Class to hold merged data
    static class MergedData {
        private final String products;
        private final String reviews;
        private final String inventory;

        public MergedData(String products, String reviews, String inventory) {
            this.products = products;
            this.reviews = reviews;
            this.inventory = inventory;
        }

        public String getProducts() {
            return products;
        }

        public String getReviews() {
            return reviews;
        }

        public String getInventory() {
            return inventory;
        }
    }
}
