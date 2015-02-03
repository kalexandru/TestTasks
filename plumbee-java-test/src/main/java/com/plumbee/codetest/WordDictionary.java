package com.plumbee.codetest;

/**
 * Implementations of this interface guarantee to be thread safe.
 */
public interface WordDictionary {

    /**
     * Check word presents in dictionary
     * @param word
     *              - the word to check
     * @return true if the dictionary contains the word
     */
    public boolean contains(String word);

    public int size();
}
