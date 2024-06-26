package com.chuwa.exercise.t08_multithreading.codingHomework;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// assume we are using the jsonplaceholder.typicode.com API to
// simulate the three different endpoints: products, reviews, and inventory.
public class FetchMergeData {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(FetchMergeData.class.getName()); // with exception handle

    public static void main(String[] args) {
        CompletableFuture<JsonNode> productsFuture = fetchJson("https://jsonplaceholder.typicode.com/posts")
                .exceptionally(ex -> handleException(ex, "products"));
        CompletableFuture<JsonNode> reviewsFuture = fetchJson("https://jsonplaceholder.typicode.com/comments")
                .exceptionally(ex -> handleException(ex, "reviews"));
        CompletableFuture<JsonNode> inventoryFuture = fetchJson("https://jsonplaceholder.typicode.com/albums")
                .exceptionally(ex -> handleException(ex, "inventory"));

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);
        allFutures.thenRun(() -> {
            try {
                JsonNode products = productsFuture.get();
                JsonNode reviews = reviewsFuture.get();
                JsonNode inventory = inventoryFuture.get();

                System.out.println("Products: " + products.toString());
                System.out.println("Reviews: " + reviews.toString());
                System.out.println("Inventory: " + inventory.toString());

                // Further processing can be done here

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join(); // Ensures the main thread waits for the completion
    }


    private static CompletableFuture<JsonNode> fetchJson(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    try {
                        return objectMapper.readTree(body);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static JsonNode handleException(Throwable ex, String apiName) {
        logger.log(Level.SEVERE, "Exception occurred while fetching data from " + apiName + " API: " + ex.getMessage());
        // Returning an empty JSON object as a default value
        return objectMapper.createObjectNode();
    }
}
