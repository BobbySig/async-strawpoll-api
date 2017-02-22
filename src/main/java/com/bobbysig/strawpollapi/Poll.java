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

        private DupcheckValue(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }

    public int id = -1;
    public String title;
    public List<String> options;
    public List<Integer> votes;
    public final boolean multi = false;
    public final DupcheckValue dupcheck = DupcheckValue.normal;
    public final boolean captcha = false;

    public Poll(String title, List<String> options) {
        this.title = title;
        this.options = options;
    }

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

        return id == poll.id;
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
