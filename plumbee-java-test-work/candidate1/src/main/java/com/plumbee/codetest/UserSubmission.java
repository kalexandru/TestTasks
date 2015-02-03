package com.plumbee.codetest;

public class UserSubmission implements Comparable<UserSubmission> {

    private final String username;
    private final String word;
    private final Integer score;
    private final Long order;

    public UserSubmission(String username, String word, int score, long order) {
        this.username = username;
        this.word = word;
        this.score = score;
        this.order = order;
    }

    public String getUsername() {
        return username;
    }

    public String getWord() {
        return word;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public int compareTo(UserSubmission o) {

        // first, by score
        if (this.score.compareTo(o.getScore()) != 0) {
            return o.getScore().compareTo(this.score);
        }

        // second by order
        return this.order.compareTo(o.order);
    }
}
