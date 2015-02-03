package com.plumbee.codetest.model;

public class UserResult implements Comparable<UserResult> {

    private String userName;
    private int score;
    private String word;

    public UserResult(String userName, int score, String word) {
        this.userName = userName;
        this.score = score;
        this.word = word;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(UserResult o) {
        if(o==null ){
            return 1;
        }
        return Integer.compare(o.getScore(),score);
    }
}
