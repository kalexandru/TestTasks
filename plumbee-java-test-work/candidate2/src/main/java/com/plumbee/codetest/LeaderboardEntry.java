package com.plumbee.codetest;

import java.util.Date;

public class LeaderboardEntry implements Comparable<LeaderboardEntry>
{
	private String username;
	private String playedWord;
	private boolean userSubmition = false;
	private int score = -1;
	private Date submitionTimestamp;

	public Date getSubmitionTimestamp()
	{
		return this.submitionTimestamp;
	}

	public void setSubmitionTimestamp(Date submitionTimestamp)
	{
		this.submitionTimestamp = submitionTimestamp;
	}

	public int getScore()
	{
		return this.score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public boolean isUserSubmition()
	{
		return this.userSubmition;
	}

	public void setUserSubmition(boolean userSubmition)
	{
		this.userSubmition = userSubmition;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPlayedWord()
	{
		return this.playedWord;
	}

	public void setPlayedWord(String playedWord)
	{
		this.playedWord = playedWord;
	}

	@Override
	public int compareTo(LeaderboardEntry o)
	{
		int result = o.getScore() - this.getScore();
		if (result == 0 && o.getScore() != -1)
		{
			result =  -(o.getSubmitionTimestamp().compareTo(this.getSubmitionTimestamp()));
		}

		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean result = true;

		if (obj != null && obj instanceof LeaderboardEntry)
		{
			LeaderboardEntry other = (LeaderboardEntry) obj;
			result &= (other.getUsername()!=null) && other.getUsername().equals(this.getUsername());
			result &= (other.getPlayedWord()!=null) && other.getPlayedWord().equals(this.getPlayedWord());
			result &= (other.getScore()!=-1) && other.getScore() == this.getScore();
		}
		else
		{
			result = false;
		}

		return result;
	}

	@Override
	public int hashCode()
	{
		return this.score;
	}
}
