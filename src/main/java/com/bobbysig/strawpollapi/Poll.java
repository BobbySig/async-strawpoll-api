package com.bobbysig.strawpollapi;

import java.util.List;

/**
 * A representation of a single poll. Based on the API provided by strawpoll.me.
 * Created by Bobby on 2/17/2017.
 */
public class Poll {
    public enum DupcheckValue {
        normal("normal"),
        permissive("permissive"),
        disabled("disabled");

        private final String name;

        DupcheckValue(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }

    int id = -1;
    String title;
    List<String> options;
    List<Integer> votes;
    final boolean multi = false;
    final DupcheckValue dupcheck = DupcheckValue.normal;
    final boolean captcha = false;

    public Poll(String title, List<String> options) {
        this.title = title;
        this.options = options;
    }

    /**
     * Returns the ID of this Poll given by the API.
     * @return The ID of this Poll in the API, or -1 if this Poll isn't associated with the API yet.
     */
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getVotes() {
        return votes;
    }

    public boolean isMulti() {
        return multi;
    }

    public DupcheckValue getDupcheck() {
        return dupcheck;
    }

    public boolean isCaptcha() {
        return captcha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Poll poll = (Poll) o;

        if (id != -1)
            return id == poll.id;
        return this == poll;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", options=" + options +
                ", votes=" + votes +
                '}';
    }
}
