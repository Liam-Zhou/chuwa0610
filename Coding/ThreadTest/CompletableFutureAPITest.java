package ThreadTest;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureAPITest {
    private static final String PRODUCTS_API = "https://dummyjson.com/products";
    private static final String REVIEWS_API = "https://jsonplaceholder.typicode.com/comments";
    private static final String INVENTORY_API = "https://dummyjson.com/prod2ucts";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        CompletableFuture<String> productsFuture = CompletableFuture.supplyAsync(() -> fetchData(PRODUCTS_API));
        CompletableFuture<String> reviewsFuture = CompletableFuture.supplyAsync(() -> fetchData(REVIEWS_API));
        CompletableFuture<String> inventoryFuture = CompletableFuture.supplyAsync(() -> fetchData(INVENTORY_API));

        CompletableFuture<Void> allOf = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);
        try {
            allOf.get();

            String products = productsFuture.get();
            String reviews = reviewsFuture.get();
            String inventory = inventoryFuture.get();


            System.out.println("Products: " + products);
            System.out.println("Reviews: " + reviews);
            System.out.println("Inventory: " + inventory);



        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();
        }
    }

    private static String fetchData(String apiUrl) {
        try {
            return restTemplate.getForObject(apiUrl, String.class);
        }catch (RestClientException e) {
            return "Deafult :{}";
        }

    }
}
