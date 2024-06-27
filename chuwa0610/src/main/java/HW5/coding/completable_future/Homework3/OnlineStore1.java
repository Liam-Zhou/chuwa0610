package HW5.coding.completable_future.Homework3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class OnlineStore1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> productsFuture = fetchDataAsync("posts");
        CompletableFuture<String> reviewsFuture = fetchDataAsync("comments");
        CompletableFuture<String> inventoryFuture = fetchDataAsync("todos");

        CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture).join();

        parseAndPrintProducts(productsFuture.get());
        System.out.println("Reviews Data:\n" + reviewsFuture.get());
        System.out.println("Inventory Data:\n" + inventoryFuture.get());
    }

    private static CompletableFuture<String> fetchDataAsync(String endpoint) {
        return CompletableFuture.supplyAsync(() -> {
            String apiUrl = "https://jsonplaceholder.typicode.com/" + endpoint;
            StringBuilder result = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(apiUrl).openStream()))) {
                result.append(br.lines().collect(Collectors.joining()));
            } catch (Exception e) {
                System.err.println("Exception occurred while fetching data from " + endpoint + ": " + e.getMessage());
                e.printStackTrace();
                return "[]"; // Default value
            }
            return result.toString();
        });
    }

    private static void parseAndPrintProducts(String productsData) {
        JSONArray jsonArray = new JSONArray(productsData);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject product = jsonArray.getJSONObject(i);
            System.out.println("Product " + (i + 1) + ":");
            System.out.println("Title: " + product.getString("title"));
            System.out.println("Body: " + product.getString("body"));
            System.out.println();
        }
    }
}
