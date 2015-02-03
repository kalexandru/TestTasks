package com.plumbee.codetest;

import java.util.Collections;
import java.util.Date;
import java.util.Vector;

public class LeaderBoard
{
	private final Vector<LeaderboardEntry> places;

	public LeaderBoard(int positions)
	{
		this.places = new Vector<LeaderboardEntry>(positions);

		// fill leaderBoard to avoid checking for it's size later on, on each access
		for (int i = 0; i < positions; i++)
		{
			this.places.add(new LeaderboardEntry());
		}
	}

	// should this method become public, one should add synch code
	private void add(LeaderboardEntry newEntry)
	{
		this.places.add(newEntry);
		Collections.sort(this.places);
		this.places.remove(this.places.size() - 1);
	}

	public LeaderboardEntry elementAt(int position)
	{
		synchronized (this.places)
		{
			return this.places.get(position);
		}
	}

	public void addIfAppliable(String username, String word, int score, Date playTime)
	{
		synchronized (this.places)
		{
			int lastPositionScore = this.places.get(this.places.size() - 1).getScore();

			// discard repeated submissions (i'm assuming that if a player submits the same word multiple times she/he should not be listed again for the same word)
			if (lastPositionScore < score)
			{
				LeaderboardEntry newEntry = new LeaderboardEntry();
				newEntry.setUserSubmition(true);
				newEntry.setUsername(username);
				newEntry.setPlayedWord(word);
				newEntry.setScore(score);
				newEntry.setSubmitionTimestamp(playTime);

				if (!this.places.contains(newEntry))
				{
					this.add(newEntry);
				}
			}
		}
	}

}
