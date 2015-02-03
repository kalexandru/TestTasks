package com.plumbee.codetest.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordAllowanceCheckerImplTest {

    private WordAllowanceChecker checker = new WordAllowanceCheckerImpl();

    @Test
    public void testAllowed(){
        assertTrue(checker.allowed("wxrod", "word"));
        assertFalse(checker.allowed("somenotmatchingletters", "word"));
        assertFalse(checker.allowed("wor", "word"));

    }
    @Test
    public void testAllowedWithSameLetters(){
        assertTrue(checker.allowed("woolly", "woolly"));

    }

}
