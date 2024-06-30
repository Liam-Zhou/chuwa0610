package com.chuwa.exercise.collection.hw5;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class hw2 {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> productsFuture = fetchFromAPI(client, "https://jsonplaceholder.typicode.com/posts");
        CompletableFuture<String> reviewsFuture = fetchFromAPI(client, "https://jsonplaceholder.typicode.com/comments?postId=1");
        CompletableFuture<String> inventoryFuture = fetchFromAPI(client, "https://jsonplaceholder.typicode.com/items");

        // Combine all futures
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allFutures.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                // Merge the data (for demonstration, just printing)
                System.out.println("Products: " + products);
                System.out.println("Reviews: " + reviews);
                System.out.println("Inventory: " + inventory);

                // Here you can further process the merged data as needed

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // Wait for all futures to complete (optional, for demo purposes)
        allFutures.join();
    }

    private static CompletableFuture<String> fetchFromAPI(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
