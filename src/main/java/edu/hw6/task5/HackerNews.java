package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final int TIMEOUT_SECONDS = 10;
    private static final String TOP_STORIES_URL_STRING = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String NEWS_URL_STRING = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final Pattern TITLE_PATTERN = Pattern.compile("\"" + "title" + "\"" + ":" + "\"" + "[^\"]*" + "\"");

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() throws InterruptedException {
        HttpRequest request = buildGetRequest(TOP_STORIES_URL_STRING);

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
        } catch (IOException e) {
            return new long[] {};
        }
    }

    public static String news(long id) throws InterruptedException {
        HttpRequest request = buildGetRequest(
            NEWS_URL_STRING.formatted(id)
        );

        try (var httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            // Get title using regexp
            Matcher matcher = TITLE_PATTERN.matcher(responseBody);
            if (matcher.find()) {
                // Get value of "title" and remove double quotes
                String temp = matcher.group().split(":")[1];
                return temp.substring(1, temp.length() - 1);
            }
            return TITLE_PATTERN.matcher(responseBody).group(1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest buildGetRequest(String urlString) {
        return HttpRequest.newBuilder()
            .uri(URI.create(urlString))
            .GET()
            .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
            .build();
    }
}
