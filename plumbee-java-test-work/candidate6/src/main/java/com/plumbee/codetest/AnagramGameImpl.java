package com.plumbee.codetest;

import java.util.ArrayList;
import java.util.Arrays;

public class AnagramGameImpl implements AnagramGame {

    private static final int SCORE_COUNT_LIMIT = 10;

    private String letters;
    private WordDictionary dictionary;
    private LeaderBoard leaderBoard;


    public AnagramGameImpl(String letters, WordDictionary dictionary) {
        this.letters = letters;
        this.dictionary = dictionary;
        leaderBoard = new LeaderBoard();
    }

    @Override
    public int submitWord(String username, String word) {
        int score = 0;
        if (isAnagram(letters, word) && dictionary.contains(word)) {
            score = word.length();
            leaderBoard.updateScores(username, word, score);
        }
        return score;
    }

    @Override
    public String getUserNameAtPosition(int position) {
        if (position > leaderBoard.getScoresInOrder().size() - 1) {
            return null;
        }
        return leaderBoard.getScoresInOrder().get(position).getUsername();
    }

    @Override
    public String getWordEntryAtPosition(int position) {
        if (position > leaderBoard.getScoresInOrder().size() - 1) {
            return null;
        }
        return leaderBoard.getScoresInOrder().get(position).getWord();
    }

    @Override
    public Integer getScoreAtPosition(int position) {
        if (position > leaderBoard.getScoresInOrder().size() - 1) {
            return null;
        }
        return leaderBoard.getScoresInOrder().get(position).getScore();
    }

    /*
     * @param a     Set of letters
     * @param b     Word checked against 'a'
     * @return boolean whether b's letters are in 'a'
    * */
    protected static boolean isAnagram(String a, String b) {
        ArrayList<Character> aList = new ArrayList<Character>(Arrays.asList(toCharacterArray(a)));
        ArrayList<Character> bList = new ArrayList<Character>(Arrays.asList(toCharacterArray(b)));

        for (Character c : aList) {
            if (bList.contains(c)) {
                    bList.remove(c);
            }
            if (bList.size() == 0) {
                return true;
            }
        }
        return false;
    }

    protected static Character[] toCharacterArray(String s) {
        if (s == null) {
            return null;
        }
        Character[] array = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            array[i] = new Character(s.charAt(i));
        }
        return array;
    }

    class ScoreEntry {
        private String username;
        private String word;
        private int score;

        ScoreEntry(String username, String word, int score) {
            this.username = username;
            this.word = word;
            this.score = score;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    class LeaderBoard {
        private int currentScoreLimit = 0;
        private ArrayList<ScoreEntry> scoresInOrder;

        public LeaderBoard() {
            this.scoresInOrder = new ArrayList<ScoreEntry>();
        }

        public synchronized ArrayList<ScoreEntry> getScoresInOrder() {
            return scoresInOrder;
        }

        public synchronized void updateScores(String username, String word, int score) {

            // if we are not in the top X don't even bother
            if (currentScoreLimit > score
                || (currentScoreLimit == score && scoresInOrder.size() == SCORE_COUNT_LIMIT)) {
                return;
            }

            // let's see where our new score fits in
            int count = 0;
            boolean scoreRegistered = false;
            for (ScoreEntry currentScore : scoresInOrder) {

                // we found the highest score we are beating
                if (score > currentScore.getScore()) {

                    int size = scoresInOrder.size();
                    if (size == SCORE_COUNT_LIMIT) {
                        // remove the last entry if we have X already
                        scoresInOrder.remove(scoresInOrder.get(size-1));
                    }
                    // add the new score
                    scoresInOrder.add(count, new ScoreEntry(username, word, score));
                    scoreRegistered = true;
                    break;
                }
                count++;
            }

            // haven't beaten any scores but we still have room in top X
            if (!scoreRegistered  && scoresInOrder.size() < SCORE_COUNT_LIMIT) {
                // add the new score
                scoresInOrder.add(new ScoreEntry(username, word, score));
            }

            // update the score limit used for quick check
            if (scoresInOrder.size() == SCORE_COUNT_LIMIT) {
                currentScoreLimit = scoresInOrder.get(scoresInOrder.size()-1).getScore();
            }
        }

    }
}
