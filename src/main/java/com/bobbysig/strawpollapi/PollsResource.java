package com.bobbysig.strawpollapi;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ListenableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * A wrapper around the strawpoll.me API's Polls endpoint.
 * @author Bobby Signor
 */
public class PollsResource {
    private static final String USER_AGENT = "Async Java Straw Poll API Client";

    private static Gson gson;
    private static AsyncHttpClient httpClient = new DefaultAsyncHttpClient();
    private String baseUrl;
    private HttpHeaders headers = new DefaultHttpHeaders();

    PollsResource(String baseUrl) {
        this.baseUrl = baseUrl + "/polls";
        headers.add("Content-Type", "application/json");
        headers.add("User-Agent", USER_AGENT);
        gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        return fieldAttributes.getName().equals("id");
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .create();
    }

    /**
     * Performs an HTTP GET request on the polls endpoint.
     * @param p The poll to request. This poll is not modified.
     * @return A {@link CompletableFuture<Poll>} that will contain the requested Poll or will throw an error.
     */
    public CompletableFuture<Poll> get(Poll p) {
        return get(p.id);
    }

    /**
     * Performs an HTTP GET request on the polls endpoint.
     * @param id The ID of the poll to request.
     * @return A {@link CompletableFuture<Poll>} that will contain the requested Poll or will throw an error.
     */
    public CompletableFuture<Poll> get(int id) {
        String url = baseUrl + "/" + Integer.toString(id);
        return httpClient
                .prepareGet(url)
                .setHeaders(headers)
                .execute()
                .toCompletableFuture()
                .thenCompose(response -> {
                    Poll p = gson.fromJson(response.getResponseBody(), Poll.class);
                    return CompletableFuture.completedFuture(p);
                });
    }

    /**
     * Performs an HTTP POST request on the polls endpoint to create a new {@link Poll}.
     * @param p The {@link Poll} to send to the API.
     * @return A {@link CompletableFuture<Poll>} that will contain the newly created {@link Poll} or will throw an error.
     */
    public CompletableFuture<Poll> post(Poll p) {
        String msgBody = gson.toJson(p);
        return httpClient
                .preparePost(baseUrl)
                .setHeaders(headers)
                .setBody(msgBody)
                .execute()
                .toCompletableFuture()
                .thenCompose(response -> {
                    Poll np = gson.fromJson(response.getResponseBody(), Poll.class);
                    return CompletableFuture.completedFuture(np);
                });
    }
}
