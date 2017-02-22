package com.bobbysig.strawpollapi;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests for {@link Poll}.
 * @author Bobby Signor
 */
public class PollTest {
    private int testId = 0;
    private String testTitle = "Is this a poll?";
    private String[] testOptions = {"Yes", "No", "Turnip"};
    private Integer[] testVotes = {1, 2, 3};

    private Poll p;

    @org.junit.Before
    public void setUp() throws Exception {
        p = new Poll(testTitle, Arrays.asList(testOptions));
        p.id = testId;
        p.votes = Arrays.asList(testVotes);
    }

    @org.junit.Test
    public void equalsHashCodeTest() throws Exception {
        Poll p2 = new Poll(testTitle, Arrays.asList(testOptions));
        p2.id = testId;
        p2.votes = Arrays.asList(testVotes);
        assertTrue("Polls with identical ID's should have the same hash code",
                p.hashCode() == p2.hashCode());
        assertTrue("Poll.equals() should return true if passed a Poll with the same ID",
                p.equals(p2));

        p2.id += 1;
        assertFalse("Polls without identical ID's should not have the same hash code",
                p.hashCode() == p2.hashCode());
        assertFalse("Poll.equals() should return false if passed a Poll a different ID",
                p.equals(p2));
    }
}
