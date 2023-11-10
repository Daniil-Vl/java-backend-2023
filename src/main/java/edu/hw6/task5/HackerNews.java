package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final int TIMEOUT = 10;

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        String urlString = "https://hacker-news.firebaseio.com/v0/topstories.json";

        // Create http request
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .GET()
                .timeout(Duration.ofSeconds(TIMEOUT))
                .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Create httpclient, send request and parse body response
        try (var httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            String[] codes = responseBody.substring(1, responseBody.length() - 1).split(",");
            long[] result = new long[codes.length];
            for (int i = 0; i < codes.length; i++) {
                result[i] = Long.parseLong(codes[i]);
            }

            return result;
        } catch (IOException | InterruptedException e) {
            return new long[] {};
        }
    }

    public static String news(long id) {
        String urlString = "https://hacker-news.firebaseio.com/v0/item/%d.json".formatted(id);

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .GET()
                .timeout(Duration.ofSeconds(TIMEOUT))
                .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try (var httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            // Get title using regexp
            Pattern pattern = Pattern.compile("\"" + "title" + "\"" + ":" + "\"" + "[^\"]*" + "\"");
            Matcher matcher = pattern.matcher(responseBody);
            if (matcher.find()) {
                // Get value of "title" and remove double quotes
                String temp = matcher.group().split(":")[1];
                return temp.substring(1, temp.length() - 1);
            }
            return pattern.matcher(responseBody).group(1);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
