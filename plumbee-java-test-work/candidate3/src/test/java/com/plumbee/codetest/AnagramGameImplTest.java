package com.plumbee.codetest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnagramGameImplTest {

	@Mock
	private WordDictionary	dictionary;

	private AnagramGameImpl	game;

	@Before
	public void before() {
		game = new AnagramGameImpl("myword", dictionary);
	}

	@Test
	public void testIsAnagram() {
		assertTrue(game.isAnagram("drow"));
		assertTrue(game.isAnagram("drowmy"));
		assertTrue(game.isAnagram("my"));
		assertFalse(game.isAnagram("mywors"));
		assertFalse(game.isAnagram("mywordd"));
	}

	@Test
	public void whenWordInvalidThenNotAddedToLeaderboard() {
		when(dictionary.contains(anyString())).thenReturn(true);
		game.submitWord("sam", "my");
		assertEquals(1, game.size());

		when(dictionary.contains(anyString())).thenReturn(false);
		game.submitWord("sam", "coldplay");
		assertEquals(1, game.size());
	}

	@Test
	public void whenWordValidThenAddedToLeaderboard() {
		when(dictionary.contains(anyString())).thenReturn(true);

		game.submitWord("sam", "my");
		assertEquals(2, game.getScoreAtPosition(0).intValue());
		assertEquals("my", game.getWordEntryAtPosition(0));

		game.submitWord("chris martin", "myw");
		assertEquals(3, game.getScoreAtPosition(0).intValue());
		assertEquals("chris martin", game.getUserNameAtPosition(0));

		game.submitWord("sam", "m");
		assertEquals(1, game.getScoreAtPosition(2).intValue());
		assertEquals("m", game.getWordEntryAtPosition(2));

	}
}
