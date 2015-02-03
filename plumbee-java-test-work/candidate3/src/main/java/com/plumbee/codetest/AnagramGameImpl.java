package com.plumbee.codetest;

import com.plumbee.codetest.Leaderboard.Position;

import java.util.Arrays;

public class AnagramGameImpl implements AnagramGame {

	private static final int	CAPACITY	= 10;
	private final Leaderboard	leaderboard	= new Leaderboard(CAPACITY);
	private final WordDictionary	dictionary;
	private final String		sorted;

	public AnagramGameImpl(String source, WordDictionary dictionary) {
		char[] chars = source.toCharArray();
		Arrays.sort(chars);
		sorted = new String(chars);
		this.dictionary = dictionary;
	}

	@Override
	public Integer getScoreAtPosition(int position) {
		return leaderboard.getScoreAtPosition(position);
	}

	@Override
	public String getUserNameAtPosition(int position) {
		return leaderboard.getUserNameAtPosition(position);
	}

	@Override
	public String getWordEntryAtPosition(int position) {
		return leaderboard.getWordEntryAtPosition(position);
	}

	public int size() {
		return leaderboard.size();
	}

	boolean isAnagram(String word) {
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		int k = 0;
		for (char letter : chars) {
			int index = sorted.indexOf(letter, k);
			if (index == -1)
				return false;
			k = index + 1;
		}
		return true;
	}

	@Override
	public int submitWord(String username, String word) {
		if (isAnagram(word)) {
			if (dictionary.contains(word)) {
				leaderboard.offer(new Position(word.length(), username, word));
				return word.length();
			}
		}
		return 0;
	}
}
