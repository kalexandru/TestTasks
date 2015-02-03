package com.plumbee.codetest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A thread-safe container for the top ten submissions.
 */
class Leaderboard {

    private static final int CAPACITY = 10;

    private final List<Submission> submissions = new ArrayList<>();

    public synchronized void add(Submission submission) {
        submissions.add(submission);
        Collections.sort(submissions);
        if (submissions.size() > CAPACITY) {
            submissions.remove(CAPACITY);
        }
    }

    public synchronized Submission get(int position) {
        if (position < 0 || position > CAPACITY - 1) {
            throw new IllegalArgumentException("invalid position: " + position);
        }
        if (position >= submissions.size()) {
            return null;
        }
        return submissions.get(position);
    }

}
