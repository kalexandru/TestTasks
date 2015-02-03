package com.plumbee.codetest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnagramGameImplTest {
    public static final String USER_1 = "user1";
    public static final String USER_2 = "user2";
    public static final String USER_3 = "user3";
    public static final String USER_4 = "user4";
    public static final String USER_5 = "user5";
    public static final String EXAMPLEWORD = "exampleword";
    public static final String EXAMPLE = "example";
    public static final String WORD = "word";
    private WordDictionary mockedDictionary = mock(WordDictionary.class);
    private AnagramGameImpl anagramGame = new AnagramGameImpl("exampleword", mockedDictionary);

    @Test
    public void testSubmitAllowedWord() throws Exception {
        when(mockedDictionary.contains("word")).thenReturn(true);
        int user1Score = anagramGame.submitWord("user1", "word");
        assertEquals(4, user1Score);
    }

    @Test
    public void testSubmitNotExistingWord() throws Exception {
        when(mockedDictionary.contains("xample")).thenReturn(false);
        int user1Score = anagramGame.submitWord("user1", "xample");
        assertEquals(0, user1Score);
    }

    @Test
    public void testSubmitExistingWordMadeOfNotAllowedLetters() throws Exception {
        when(mockedDictionary.contains("weird")).thenReturn(true);
        int user1Score = anagramGame.submitWord("user1", "weird");
        assertEquals(0, user1Score);
    }

    @Test
    public void testGetUserNameAtPosition() throws Exception {
        simulateGame();

        assertEquals(USER_2, anagramGame.getUserNameAtPosition(0));
        assertEquals(USER_3, anagramGame.getUserNameAtPosition(1));
        assertEquals(USER_5, anagramGame.getUserNameAtPosition(2));

        //I assume order of players with the same score is random
        // - may not be the case in reality
        String looser1 = anagramGame.getUserNameAtPosition(3);
        String looser2 = anagramGame.getUserNameAtPosition(4);
        boolean looser1Present = USER_1.equals(looser1) || USER_4.equals(looser1);
        boolean looser2Present = USER_1.equals(looser2) || USER_4.equals(looser2);

        assertTrue(looser2Present);
        assertTrue(looser1Present);
        assertNull(anagramGame.getUserNameAtPosition(5));
    }


    @Test
    public void testGetWordEntryAtPosition() throws Exception {
        simulateGame();

        assertEquals(EXAMPLEWORD, anagramGame.getWordEntryAtPosition(0));
        assertEquals(EXAMPLE, anagramGame.getWordEntryAtPosition(1));
        assertEquals(WORD, anagramGame.getWordEntryAtPosition(2));
        assertNull(anagramGame.getWordEntryAtPosition(5));
    }
    @Test
    public void testGetScoreAtPosition() throws Exception {
        simulateGame();

        assertEquals((Integer)EXAMPLEWORD.length(), anagramGame.getScoreAtPosition(0));
        assertEquals((Integer)EXAMPLE.length(), anagramGame.getScoreAtPosition(1));
        assertEquals((Integer)WORD.length(), anagramGame.getScoreAtPosition(2));
        assertNull(anagramGame.getScoreAtPosition(5));
    }

    private void simulateGame() {
        when(mockedDictionary.contains("example")).thenReturn(true);
        when(mockedDictionary.contains("exampleword")).thenReturn(true);
        when(mockedDictionary.contains("word")).thenReturn(true);
        anagramGame.submitWord(USER_1, "weird");
        anagramGame.submitWord(USER_2, EXAMPLEWORD);
        anagramGame.submitWord(USER_3, EXAMPLE);
        anagramGame.submitWord(USER_4, "bla");
        anagramGame.submitWord(USER_5, WORD);

        String firstPlaceUser = anagramGame.getUserNameAtPosition(0);
        String secondPlaceUser = anagramGame.getUserNameAtPosition(1);
        String thirdPlaceUser = anagramGame.getUserNameAtPosition(2);
        String fourthPlaceUser = anagramGame.getUserNameAtPosition(3);
        String fifthPlaceUser = anagramGame.getUserNameAtPosition(4);
    }
}
