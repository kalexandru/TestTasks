package com.plumbee.codetest;

import java.util.ArrayList;
import java.util.List;

public class AnagramGameImpl implements AnagramGame {

    private final String originalString;
    private final WordDictionary dictionary;
    private final Leaderboard leaderboard = new Leaderboard();

    public AnagramGameImpl(String originalString, WordDictionary dictionary) {
        this.originalString = originalString;
        this.dictionary = dictionary;
    }

    @Override
    public int submitWord(String username, String word) {
        if (isPartialAnagram(word) && dictionary.contains(word)) {
            int score = word.length();
            leaderboard.add(new Submission(username, word, score));
            return score;
        }
        return 0;
    }

    @Override
    public String getUserNameAtPosition(int position) {
        Submission submission = leaderboard.get(position);
        return submission != null ? submission.getUsername() : null;
    }

    @Override
    public String getWordEntryAtPosition(int position) {
        Submission submission = leaderboard.get(position);
        return submission != null ? submission.getWord() : null;
    }

    @Override
    public Integer getScoreAtPosition(int position) {
        Submission submission = leaderboard.get(position);
        return submission != null ? submission.getScore() : null;
    }

    private boolean isPartialAnagram(String word) {
        List<Character> remainingLetters = new ArrayList<>();
        for (char c : originalString.toCharArray()) {
            remainingLetters.add(Character.valueOf(c));
        }
        for (char c : word.toCharArray()) {
            Character letter = Character.valueOf(c);
            if (!remainingLetters.contains(letter)) {
                return false;
            }
            remainingLetters.remove(letter);
        }
        return true;
    }

}
