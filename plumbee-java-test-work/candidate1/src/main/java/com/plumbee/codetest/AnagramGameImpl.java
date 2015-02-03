package com.plumbee.codetest;

import java.util.*;

public class AnagramGameImpl implements AnagramGame {

    private final int NOT_ACCEPTED_CODE = 0;
    private final int TOP_LENGTH = 10;
    private final Object lock = new Object();
    // a chache with allowed letters
    private final Set<Character> baseLettersIndex;
    // the dictionary
    private WordDictionary dictionary;
    // the score list
    private final List<UserSubmission> overallScore;
    // the words submission counter
    private final Hashtable<String, Long> wordsSubmOrder;
    //  top-ten
    private final List<UserSubmission> top;

    /**
     * Constructs a new instance of this object
     *
     * @param baseLetters the base set of letters
     * @param dictionary the dictionary to check the submissions
     */
    public AnagramGameImpl(String baseLetters, WordDictionary dictionary) throws Exception {

        if (baseLetters == null || baseLetters.isEmpty()) {
            throw new Exception("Invalid base letters sequence");
        }

        if (dictionary == null) {
            throw new Exception("The dictionary cannot be null");
        }

        baseLettersIndex = new HashSet<>();
        overallScore = new LinkedList<UserSubmission>();
        top = new ArrayList<>(TOP_LENGTH);
        wordsSubmOrder = new Hashtable<String, Long>();

        char[] chars = baseLetters.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            baseLettersIndex.add(new Character(chars[i]));
        }

        this.dictionary = dictionary;
    }

    /**
     * Submit a word on behalf of a user. A word is accepted if its letters are
     * contained in the original string submitted in the constructor, and if it
     * is in the WordDictionary. If the word is accepted and its score is high
     * enough, the user's submission should be added to the top-ten leaderboard.
     * If there are multiple submissions with the same score, all are accepted,
     * but the first submission with that score should rank higher. If there are
     * multiple submissions with the same username, each should be treated as a
     * separate entry and recorded independently.
     * <p/>
     * NOTE: This method must be threadsafe; multiple users may be submitting at
     * the same time.
     *
     * @param username Name to record the submission under
     * @param word User's submission. All submissions may be assumed to be
     * lowercase and containing no whitespace or special characters.
     * @return the user's score, 0 if the word is not accepted
     */
    @Override
    public int submitWord(String username, String word) {

        // do not allow nullable usernames or words
        if (username == null || word == null) {
            throw new NullPointerException("Usernames and Words cannot be null");
        }

        // it is in the dictionary
        if (!dictionary.contains(word)) {
            return NOT_ACCEPTED_CODE;
        }

        // verify if the letters are contained in the original string
        int wordScore = 0;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!baseLettersIndex.contains(chars[i])) {
                return NOT_ACCEPTED_CODE;
            }
            wordScore++;
        }

        synchronized (lock) {

            // calculates how many equal words have been submitted
            long wordOrder = (wordsSubmOrder.containsKey(word) ? wordsSubmOrder.get(word) : 0);
            wordOrder++;
            wordsSubmOrder.put(word, wordOrder);

            // saves the user's submission
            UserSubmission userSubm = new UserSubmission(username, word, wordScore, wordOrder);
            overallScore.add(userSubm);

            // sorts the score (desc order) - there are more efficent ways of doing this (e.g. sorted linked list)
            Collections.sort(overallScore);

            // creates the top
            top.clear();
            for (int i = 0; (i < TOP_LENGTH) && (i < overallScore.size()); i++) {
                top.add(overallScore.get(i));
            }

            return userSubm.getScore();
        }
    }

    /**
     * Return username at given position in the leaderboard, 0 being the highest
     * (best score) and 9 the lowest. You may assume that this method will never
     * be called with position > 9.
     *
     * @param position Index on leaderboard
     * @return username at given position in the leaderboard, or null if there
     * is no entry at that position
     */
    @Override
    public String getUserNameAtPosition(int position) {

        if (position < 0 || position > (TOP_LENGTH - 1)) {
            return null;
        }

        synchronized (lock) {
            return top.get(position).getUsername();
        }
    }

    /**
     * Return word entry at given position in the leaderboard, 0 being the
     * highest (best score) and 9 the lowest. You may assume that this method
     * will never be called with position > 9.
     *
     * @param position Index on leaderboard
     * @return word entry at given position in the leaderboard, or null if there
     * is no entry at that position
     */
    @Override
    public String getWordEntryAtPosition(int position) {

        if (position < 0 || position > (TOP_LENGTH - 1)) {
            return null;
        }

        synchronized (lock) {
            return top.get(position).getWord();
        }
    }

    /**
     * Return score at given position in the leaderboard, 0 being the highest
     * (best score) and 9 the lowest. You may assume that this method will never
     * be called with position > 9.
     *
     * @param position Index on leaderboard
     * @return score at given position in the leaderboard, or null if there is
     * no entry at that position
     */
    @Override
    public Integer getScoreAtPosition(int position) {

        if (position < 0 || position > (TOP_LENGTH - 1)) {
            return null;
        }

        synchronized (lock) {
            return top.get(position).getScore();
        }
    }
}
