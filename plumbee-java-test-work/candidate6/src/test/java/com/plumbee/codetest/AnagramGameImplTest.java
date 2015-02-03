package com.plumbee.codetest;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnagramGameImplTest {

    class MockDictionary implements WordDictionary {
        @Override
        public boolean contains(String word) {

            return true;
        }

        @Override
        public int size() {
            return 0;
        }
    }

    @Test
    public void testIsAnagram() {

        Assert.assertTrue(AnagramGameImpl.isAnagram("abc", "a"));
        Assert.assertTrue(AnagramGameImpl.isAnagram("abc", "bc"));
        Assert.assertTrue(AnagramGameImpl.isAnagram("abcd", "abcd"));

        Assert.assertFalse(AnagramGameImpl.isAnagram("ab", "abc"));
        Assert.assertFalse(AnagramGameImpl.isAnagram("abcd", "abb"));
        Assert.assertFalse(AnagramGameImpl.isAnagram("abcd", "s"));

    }

    @Test
    public void testUpdateScore() {

        WordDictionary dictionaryAlwaysTrue = new MockDictionary();

        AnagramGame ag = new AnagramGameImpl("abcdefghijklm", dictionaryAlwaysTrue);

        ag.submitWord("1", "a");

        Assert.assertEquals(1,(int)ag.getScoreAtPosition(0));
        Assert.assertEquals("1",ag.getUserNameAtPosition(0));
        Assert.assertEquals("a",ag.getWordEntryAtPosition(0));
        ag.submitWord("2", "ab");
        Assert.assertEquals(2, (int) ag.getScoreAtPosition(0));
        Assert.assertEquals("2",ag.getUserNameAtPosition(0));
        Assert.assertEquals("ab",ag.getWordEntryAtPosition(0));
        Assert.assertEquals(1,(int)ag.getScoreAtPosition(1));
        Assert.assertEquals("1",ag.getUserNameAtPosition(1));
        Assert.assertEquals("a",ag.getWordEntryAtPosition(1));
        ag.submitWord("3", "abc");
        ag.submitWord("4", "abcd");
        ag.submitWord("5", "abcde");
        ag.submitWord("6", "abcdef");
        ag.submitWord("7", "abcdefg");
        ag.submitWord("8", "abcdefgh");
        ag.submitWord("9", "abcdefghi");
        ag.submitWord("10", "abcdefghij");
        ag.submitWord("11", "abcdefghijk");
        Assert.assertEquals(11, (int) ag.getScoreAtPosition(0));
        Assert.assertEquals("11",ag.getUserNameAtPosition(0));
        Assert.assertEquals("abcdefghijk",ag.getWordEntryAtPosition(0));
        Assert.assertEquals(10, (int) ag.getScoreAtPosition(1));
        Assert.assertEquals(2, (int) ag.getScoreAtPosition(9));
    }


    @Test
    public void test() {
    }


}
