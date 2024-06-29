package com.chuwa.learn;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Q25b {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String PRODUCTS_API = "https://jsonplaceholder.typicode.com/posts";
    private static final String REVIEWS_API = "https://jsonplaceholder.typicode.com/comments";
    private static final String INVENTORY_API = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        try {
            CompletableFuture<String> productsFuture = fetchData(PRODUCTS_API);
            CompletableFuture<String> reviewsFuture = fetchData(REVIEWS_API);
            CompletableFuture<String> inventoryFuture = fetchData(INVENTORY_API);

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

            allFutures.thenRun(() -> {
                try {
                    String productsData = productsFuture.get();
                    String reviewsData = reviewsFuture.get();
                    String inventoryData = inventoryFuture.get();

                    String mergedData = "Merged Data: " + productsData + reviewsData + inventoryData;
                    System.out.println(mergedData);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }).join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CompletableFuture<String> fetchData(String apiUrl) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(apiUrl)).GET().build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}

