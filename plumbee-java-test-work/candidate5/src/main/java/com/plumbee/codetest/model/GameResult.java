package com.plumbee.codetest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameResult {
    private List<UserResult> results = new ArrayList<UserResult>();

    public void addResult(UserResult result) {
        results.add(result);
        Collections.sort(results);
    }


    public String getUserNameAtPosition(int position) {
        if (position >= results.size()) {
            return null;
        }
        return results.get(position).getUserName();
    }

    public Integer getScoreAtPosition(int position) {
        if (position >= results.size()) {
            return null;
        }
        return results.get(position).getScore();
    }

    public String getWordEntryAtPosition(int position) {
        if (position >= results.size()) {
            return null;
        }
        return results.get(position).getWord();
    }
}
