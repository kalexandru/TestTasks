package com.plumbee.codetest.utils;

import java.util.Map;

public class WordAllowanceCheckerImpl implements WordAllowanceChecker {

    @Override
    public boolean allowed(String allowedLetters, String word) {
        String lettersLeft = allowedLetters;
        for(int i=0;i<word.length();i++){
            char letterToCheck = word.charAt(i);

            if(lettersLeft.contains(Character.toString(letterToCheck))){
                lettersLeft =
                        lettersLeft.replaceFirst(Character.toString(letterToCheck),"_");
            } else {
                return false;
            }
        }
        return true;
    }

}
