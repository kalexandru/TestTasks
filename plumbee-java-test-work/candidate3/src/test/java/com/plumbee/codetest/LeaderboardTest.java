package com.plumbee.codetest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.plumbee.codetest.Leaderboard.Position;

public class LeaderboardTest {

	@Test
	public void testCapacity() {
		Leaderboard l = new Leaderboard(3);
		l.offer(new Position(1, "sam", "hello"));
		l.offer(new Position(2, "sam", "amazeballs"));
		l.offer(new Position(3, "sam", "hello"));
		assertEquals(3, l.size());
		l.offer(new Position(4, "sam", "amazeballs"));
		assertEquals(3, l.size());
	}

	@Test
	public void testOffer() {
		Leaderboard l = new Leaderboard(5);
		l.offer(new Position(1, "sam", "amazeballs"));
		l.offer(new Position(2, "jam", "amazeballs2"));
		l.offer(new Position(3, "ham", "amazeballs3"));
		l.offer(new Position(4, "spam", "amazeballs4"));
		l.offer(new Position(5, "can", "amazeballs5"));
		assertEquals(5, l.size());
		assertEquals("can", l.getUserNameAtPosition(0));
		assertEquals("sam", l.getUserNameAtPosition(4));

		// should push out 1
		l.offer(new Position(6, "wham", "amazeballs"));
		assertEquals(5, l.size());
		assertEquals("wham", l.getUserNameAtPosition(0));
		assertEquals("jam", l.getUserNameAtPosition(4));

		// should not be accepted
		l.offer(new Position(2, "scam", "amazeballs"));
		assertEquals("wham", l.getUserNameAtPosition(0));
		assertEquals("jam", l.getUserNameAtPosition(4));
	}

	@Test
	public void testSize() {
		Leaderboard l = new Leaderboard(5);
		l.offer(new Position(1, "sam", "hello"));
		l.offer(new Position(2, "sam", "amazeballs"));
		assertEquals(2, l.size());
	}

	@Test
	public void testSyncronization() throws InterruptedException {
		final Leaderboard l = new Leaderboard(5);
		ExecutorService e = Executors.newFixedThreadPool(8);
		for (int k = 0; k < 10000; k++) {
			final int j = k;
			e.submit(new Runnable() {

				@Override
				public void run() {
					l.offer(new Position(j, "sam", "hello"));
				}
			});

		}
		e.shutdown();
		e.awaitTermination(5, TimeUnit.SECONDS);
		assertEquals(5, l.size());
		assertEquals(9999, l.getScoreAtPosition(0).intValue());
		assertEquals(9998, l.getScoreAtPosition(1).intValue());
		assertEquals(9997, l.getScoreAtPosition(2).intValue());
		assertEquals(9996, l.getScoreAtPosition(3).intValue());
		assertEquals(9995, l.getScoreAtPosition(4).intValue());
	}
}
