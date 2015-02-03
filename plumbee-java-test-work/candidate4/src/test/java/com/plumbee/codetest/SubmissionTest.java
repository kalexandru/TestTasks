package com.plumbee.codetest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubmissionTest {
    WordDictionary dictionary;
    AnagramGame game;

    public SubmissionTest() {
        dictionary = new WordDictionaryImpl();
    }

    @Before
    public void setUp() throws Exception {
        game = new AnagramGameImpl("areallylongword", dictionary);
    }

    @Test
    public void testSubmission() throws Exception {
        assertEquals(2, game.submitWord("1", "no"));
        assertEquals(4, game.submitWord("2", "grow"));
        assertEquals(0, game.submitWord("3", "bold"));
        assertEquals(0, game.submitWord("4", "glly"));
        assertEquals(6, game.submitWord("5", "woolly"));
        assertEquals(0, game.submitWord("6", "adder"));
    }

    @Test
    public void testLeaderboard() throws Exception {
        // at the start of the game the leaderboard should be empty
        for (int i = 0; i < 10; i++) {
            checkSubmissionAtPosition(i, null, null, null);
        }
        
        game.submitWord("1", "no");
        checkSubmissionAtPosition(0, "1", "no", 2);
        checkSubmissionAtPosition(1, null, null, null);
        
        game.submitWord("2", "grow");
        checkSubmissionAtPosition(0, "2", "grow", 4);
        checkSubmissionAtPosition(1, "1", "no", 2);
        checkSubmissionAtPosition(2, null, null, null);
        
        game.submitWord("3", "bold");
        checkSubmissionAtPosition(2, null, null, null);
        
        game.submitWord("5", "woolly");
        checkSubmissionAtPosition(0, "5", "woolly", 6);
        checkSubmissionAtPosition(1, "2", "grow", 4);
        checkSubmissionAtPosition(2, "1", "no", 2);
        
        // another 2-letter word from the user "1": should go after the previous one i.e. "no"
        game.submitWord("1", "on");
        checkSubmissionAtPosition(2, "1", "no", 2);
        checkSubmissionAtPosition(3, "1", "on", 2);
        
        // eight new 6-letter submissions should keep the current 1st at the top
        // and push the current 2nd to 10th place
        for (int i = 1; i <= 8; i++) {
            game.submitWord("" + i, "really");
        }
        checkSubmissionAtPosition(0, "5", "woolly", 6);
        checkSubmissionAtPosition(1, "1", "really", 6);
        checkSubmissionAtPosition(2, "2", "really", 6);
        checkSubmissionAtPosition(8, "8", "really", 6);
        checkSubmissionAtPosition(9, "2", "grow", 4);
    }

    @Test
    public void testMultipleThreads() throws Exception {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            final String user = "" + i; 
            threads[i] = new Thread() {
                @Override public void run() {
                    game.submitWord(user, "on");
                    game.submitWord(user, "really");
                    game.submitWord(user, "grow");
                }
            };
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        // leaderboard should contain all 6-letter submissions
        // users will be in "random" order depending on which thread ran first
        for (int i = 0; i < 10; i++) {
            //System.out.println(game.getUserNameAtPosition(i));
            assertEquals(Integer.valueOf(6), game.getScoreAtPosition(i));
        }
    }

    private void checkSubmissionAtPosition(int position, String username, String word, Integer score) {
        assertEquals(username, game.getUserNameAtPosition(position));
        assertEquals(word, game.getWordEntryAtPosition(position));
        assertEquals(score, game.getScoreAtPosition(position));
    }

}
