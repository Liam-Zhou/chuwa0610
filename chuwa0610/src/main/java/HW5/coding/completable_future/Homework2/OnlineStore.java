package HW5.coding.completable_future.Homework2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
public class OnlineStore {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //supplyAsync
        CompletableFuture<String> productsFuture = CompletableFuture.supplyAsync(() -> fetchData("posts"));
        CompletableFuture<String> reviewsFuture = CompletableFuture.supplyAsync(() -> fetchData("comments"));
        CompletableFuture<String> inventoryFuture = CompletableFuture.supplyAsync(() -> fetchData("todos"));

        // 等待所有 CompletableFuture 完成，并获取结果
        CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture).join();

        // 获取各自的结果
        String productsData = productsFuture.get();
        String reviewsData = reviewsFuture.get();
        String inventoryData = inventoryFuture.get();

        // 解析并打印产品数据
        parseAndPrintProducts(productsData);

        // 打印评论和库存数据
        System.out.println("Reviews Data:\n" + reviewsData);
        System.out.println("Inventory Data:\n" + inventoryData);

        // 可以在这里进行进一步的数据处理和业务逻辑
    }

    // 模拟从 API 获取数据的方法
    private static String fetchData(String endpoint) {
        String apiUrl = "https://jsonplaceholder.typicode.com/" + endpoint;
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(apiUrl).openStream()))) {
            result.append(br.lines().collect(Collectors.joining()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // 解析并打印产品数据
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
