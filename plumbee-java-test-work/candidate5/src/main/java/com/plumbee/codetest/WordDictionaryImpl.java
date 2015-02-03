package com.plumbee.codetest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

public class WordDictionaryImpl implements WordDictionary {

    Vector v = new Vector();

    public WordDictionaryImpl() {
        try {
            URL url = new URL("http://www.plumbeegames.com/codetest/wordlist.txt");
            URLConnection dc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(dc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                v.add(inputLine);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean contains(String word) {
        return v.contains(word);
    }

    @Override
    public int size() {
        return v.size();
    }
}
