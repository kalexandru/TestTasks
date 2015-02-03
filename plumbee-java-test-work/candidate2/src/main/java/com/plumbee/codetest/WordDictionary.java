package com.plumbee.codetest;

/**
 * Implementations of this interface guarantee to be threadsafe.
 */
public interface WordDictionary {

    /**
     * @param word
     * @return true if the dictionary contains the word
     */
    public boolean contains(String word);

    public int size();
}
