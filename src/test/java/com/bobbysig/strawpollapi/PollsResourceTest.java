package com.bobbysig.strawpollapi;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for {@link PollsResource}
 * @author Bobby Signor
 */
public class PollsResourceTest {
    private int testPollId = 1;
    private String testApiJsonString = "{\"id\":1,\"title\":\"What movie should we watch\",\"options\":" +
            "[\"Sucker punch\",\"Pirates of carribian\",\"Prison logic\",\"Witchhunter\"]," +
            "\"votes\":[24559,49417,10537,11763],\"multi\":false,\"dupcheck\":\"normal\",\"captcha\":false}";
    private PollsResource pollsResource;
    private Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        StrawPollApiClient s = new StrawPollApiClient();
        pollsResource = s.polls();
    }

    @Test
    public void get() throws Exception {
        Poll expected = gson.fromJson(testApiJsonString, Poll.class);
        Poll actual = pollsResource
                .get(testPollId)
                .exceptionally(t -> {
                    t.printStackTrace();
                    fail("GET request failed with the following message: " + t.getMessage());
                    return null;
                })
                .get();

        assertEquals("PollsResource.get() should return the Poll with the given ID.",
                expected, actual);

        Poll actual2 = pollsResource
                .get(actual)
                .exceptionally(t -> {
                    t.printStackTrace();
                    fail("GET request failed with the following message: " + t.getMessage());
                    return null;
                })
                .get();

        assertEquals("PollsResource.get() should return the same Poll as the one provided.",
                actual, actual2);
    }

    @Test
    public void post() throws Exception {
        String[] optionsArr = {"Yes", "No", "Turnip"};
        List<String> options = Arrays.asList(optionsArr);
        Poll testPoll = new Poll("Are the options in this poll dumb?", options);
        Poll actual = pollsResource
                .post(testPoll)
                .exceptionally(t -> {
                    t.printStackTrace();
                    fail("POST request failed with the following message: " + t.getMessage());
                    return null;
                })
                .get();

        assertEquals("The Poll returned from PollsResource.post() should have the given title.",
                testPoll.title, actual.title);
        assertEquals("The Poll returned from PollsResource.post() should have the given options.",
                testPoll.options, actual.options);
    }
}
