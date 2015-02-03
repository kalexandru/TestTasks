package com.plumbee.codetest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Leaderboard {

	static class Position implements Comparable<Position> {

		private final int		score;
		private final String	username;
		private final String	word;

		public Position(int score, String username, String word) {
			this.score = score;
			this.username = username;
			this.word = word;
		}

		@Override
		public int compareTo(Position arg0) {
			// highest number first
			return Integer.valueOf(arg0.score).compareTo(score);
		}

		public int getScore() {
			return score;
		}

		public String getUsername() {
			return username;
		}

		public String getWord() {
			return word;
		}
	}

	private final List<Position>	positions	= new ArrayList<Position>();
	private final ReadWriteLock	lock		= new ReentrantReadWriteLock();
	private final int			capacity;

	public Leaderboard(int capacity) {
		this.capacity = capacity;
	}

	public Position get(int pos) {
		Lock r = lock.readLock();
		r.lock();
		try {
			return positions.get(pos);
		} finally {
			r.unlock();
		}
	}

	public Integer getScoreAtPosition(int position) {
		return get(position).getScore();
	}

	public String getUserNameAtPosition(int position) {
		return get(position).getUsername();
	}

	public String getWordEntryAtPosition(int position) {
		return get(position).getWord();
	}

	public void offer(Position offered) {
		Lock r = lock.writeLock();
		r.lock();
		try {
			positions.add(offered);
			Collections.sort(positions);
			if (positions.size() > capacity) {
				positions.remove(positions.size() - 1);
			}
		} finally {
			r.unlock();
		}
	}

	public int size() {
		Lock r = lock.readLock();
		r.lock();
		try {
			return positions.size();
		} finally {
			r.unlock();
		}
	}
}
