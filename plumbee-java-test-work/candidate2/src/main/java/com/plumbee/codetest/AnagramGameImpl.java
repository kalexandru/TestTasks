package com.plumbee.codetest;

import java.util.Date;

public class AnagramGameImpl implements AnagramGame
{

	private final int leaderboardSize = 10;
	private final WordDictionary dictionary;
	private final String baseWord;

	private final LeaderBoard leaderBoard = new LeaderBoard(this.leaderboardSize);

	public AnagramGameImpl(String baseWord, WordDictionary dictionary)
	{
		this.baseWord = baseWord;
		this.dictionary = dictionary;

	}

	//The multithread safe code resides on the Leaderboard class
	@Override
	public int submitWord(String username, String word)
	{
		//by calculating the playTime first we avoid play score algorithm delay fluctuations maintaining entry order and subsequent score ordering fair
		Date playTime = new Date();

		int score = 0;
		if (this.dictionary.contains(word))
		{
			score = this.getScore(word);
		}

		if (score >0)
		{
			this.leaderBoard.addIfAppliable(username,word,score,playTime);
		}

		return score;
	}

	private int getScore(String word)
	{
		int score = 0;
		String workingWord = new String(this.baseWord);

		for (int i = 0; i < word.length(); ++i)
		{
			if (workingWord.indexOf(word.charAt(i)) != -1)
			{
				score++;
				workingWord = this.removeCharAt(workingWord, workingWord.indexOf(word.charAt(i)));
			}
			else
			{
				score = 0;
				break;
			}
		}
		return score;
	}

	private String removeCharAt(String s, int pos)
	{
		return s.substring(0, pos) + s.substring(pos + 1);
	}

	@Override
	public String getUserNameAtPosition(int position)
	{
		String result = null;
		LeaderboardEntry entry = this.leaderBoard.elementAt(position);

		if (entry.isUserSubmition())
		{
			result = entry.getUsername();
		}

		return result;
	}

	@Override
	public String getWordEntryAtPosition(int position)
	{
		String result = null;
		LeaderboardEntry entry = this.leaderBoard.elementAt(position);

		if (entry.isUserSubmition())
		{
			result = entry.getPlayedWord();
		}

		return result;
	}

	@Override
	public Integer getScoreAtPosition(int position)
	{
		Integer result = null;
		LeaderboardEntry entry = this.leaderBoard.elementAt(position);

		if (entry.isUserSubmition())
		{
			result = entry.getScore();
		}

		return result;
	}
}
