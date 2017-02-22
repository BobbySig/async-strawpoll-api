package com.bobbysig.strawpollapi;

/**
 * @author Bobby Signor
 */
public class StrawPollApiClient {
    public static final String DEFAULT_BASE_URL = "https://www.strawpoll.me/api/v2";

    private PollsResource pollsResource;

    public StrawPollApiClient() {
        this(DEFAULT_BASE_URL);
    }

    public StrawPollApiClient(String baseUrl) {
        pollsResource = new PollsResource(baseUrl);
    }

    public PollsResource polls() {
        return pollsResource;
    }
}
