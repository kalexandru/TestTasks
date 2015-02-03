package com.plumbee.codetest;

import com.plumbee.codetest.model.GameResult;
import com.plumbee.codetest.model.UserResult;
import com.plumbee.codetest.utils.WordAllowanceChecker;
import com.plumbee.codetest.utils.WordAllowanceCheckerImpl;

public class AnagramGameImpl implements AnagramGame {

    private String allowedLetters;
    private WordDictionary dictionary;
    //consider more flexible way of injecting the checker:
    private WordAllowanceChecker wordAllowanceChecker = new WordAllowanceCheckerImpl();
    private GameResult gameResult = new GameResult();

    /**
     * the assumption is first param string contains allowed letter -
     * my personal opinion - a bit misleading
     * @param allowedLetters
     * @param dictionary
     */
    public AnagramGameImpl(String allowedLetters, WordDictionary dictionary) {
          this.allowedLetters =allowedLetters;
        this.dictionary=dictionary;

    }

    @Override
    public int submitWord(String username, String word) {

        int result = 0;
        if(wordAllowanceChecker.allowed(allowedLetters,word)){
            if(dictionary.contains(word))
            {
                result= word.length();
            }
        }
        gameResult.addResult(new UserResult(username,result,word));

        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getUserNameAtPosition(int position) {
        return gameResult.getUserNameAtPosition(position);
    }

    @Override
    public String getWordEntryAtPosition(int position) {
        return gameResult.getWordEntryAtPosition(position);
    }

    @Override
    public Integer getScoreAtPosition(int position) {
        return gameResult.getScoreAtPosition(position);
    }
}
