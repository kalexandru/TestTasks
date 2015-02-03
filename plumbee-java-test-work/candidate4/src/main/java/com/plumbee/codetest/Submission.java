package com.plumbee.codetest;

/**
 * Immutable object containing submission details.  
 */
class Submission implements Comparable<Submission> {

    private final String username;
    private final String word;
    private final int score;
    private final long creationTime;

    public Submission(String username, String word, int score) {
        this.username = username;
        this.word = word;
        this.score = score;
        this.creationTime = System.currentTimeMillis();
    }

    public String getUsername() {
        return username;
    }

    public String getWord() {
        return word;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Submission other) {
        int diff = Integer.compare(other.score, this.score);
        if (diff == 0) {
            return Long.compare(this.creationTime, other.creationTime);
        }
        return diff;
    }

}
