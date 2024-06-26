package multithreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.CompletableFuture;

/**
 * description: FakeApi
 * date: 6/26/24 4:20 PM
 * author: jinhao_pang
 * version: 1.0
 */
public class FakeApi {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/todos/");
        CompletableFuture.supplyAsync(() -> connection(url))
                .thenApplyAsync(con -> {
                    try {
                        return con.getInputStream();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).thenApplyAsync(is ->
                {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(is));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while (true) {
                        try {
                            if ((inputLine = in.readLine()) == null) break;
                            content.append(inputLine);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    try {
                        in.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return content.toString();
                })
                .exceptionallyAsync((ex) -> {
                    System.out.println(ex.toString());
                    return "";
                }).thenAccept(System.out::println).join();
    }

    private static HttpURLConnection connection(URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return con;
        } catch (Exception ignored) {

        }
        return null;
    }
}
