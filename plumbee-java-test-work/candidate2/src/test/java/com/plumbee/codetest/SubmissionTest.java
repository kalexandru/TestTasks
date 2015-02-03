package com.plumbee.codetest;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SubmissionTest
{
	WordDictionary dictionary;
	AnagramGame service;

	public SubmissionTest()
	{
		this.dictionary = new WordDictionaryImpl();
	}

	@Before
	public void setUp() throws Exception
	{
		this.service = new AnagramGameImpl("areallylongword", this.dictionary);
	}

	@Test
	public void testSubmission() throws Exception
	{
		assertEquals(2, this.service.submitWord("1", "no"));
		assertEquals(4, this.service.submitWord("2", "grow"));
		assertEquals(0, this.service.submitWord("3", "bold"));
		assertEquals(0, this.service.submitWord("4", "glly"));
		assertEquals(6, this.service.submitWord("5", "woolly"));
		assertEquals(0, this.service.submitWord("6", "adder"));
	}

	@Test
	public void testLeaderBoardsScore() throws Exception
	{

		this.service.submitWord("1", "no");
		assertEquals(new Integer(2), this.service.getScoreAtPosition(0));
		this.service.submitWord("2", "grow");
		assertEquals(new Integer(4), this.service.getScoreAtPosition(0));
		assertEquals(new Integer(2), this.service.getScoreAtPosition(1));
		this.service.submitWord("3", "bold");
		assertEquals(new Integer(4), this.service.getScoreAtPosition(0));
		assertEquals(new Integer(2), this.service.getScoreAtPosition(1));
		this.service.submitWord("4", "glly");
		assertEquals(new Integer(4), this.service.getScoreAtPosition(0));
		assertEquals(new Integer(2), this.service.getScoreAtPosition(1));
		this.service.submitWord("5", "woolly");
		assertEquals(new Integer(6), this.service.getScoreAtPosition(0));
		assertEquals(new Integer(4), this.service.getScoreAtPosition(1));
		assertEquals(new Integer(2), this.service.getScoreAtPosition(2));
		this.service.submitWord("6", "adder");
		assertEquals(new Integer(6), this.service.getScoreAtPosition(0));
		assertEquals(new Integer(4), this.service.getScoreAtPosition(1));
		assertEquals(new Integer(2), this.service.getScoreAtPosition(2));

		assertEquals(new Integer(6), this.service.getScoreAtPosition(0));
		assertEquals(new Integer(4), this.service.getScoreAtPosition(1));
		assertEquals(new Integer(2), this.service.getScoreAtPosition(2));
		assertEquals(null, this.service.getScoreAtPosition(3));
		assertEquals(null, this.service.getScoreAtPosition(4));
		assertEquals(null, this.service.getScoreAtPosition(5));
		assertEquals(null, this.service.getScoreAtPosition(6));
		assertEquals(null, this.service.getScoreAtPosition(7));
		assertEquals(null, this.service.getScoreAtPosition(8));
		assertEquals(null, this.service.getScoreAtPosition(9));
	}

	@Test
	public void testLeaderBoardsUsernames() throws Exception
	{

		this.service.submitWord("1", "no");
		assertEquals("1", this.service.getUserNameAtPosition(0));
		this.service.submitWord("2", "grow");
		assertEquals("2", this.service.getUserNameAtPosition(0));
		assertEquals("1", this.service.getUserNameAtPosition(1));
		this.service.submitWord("3", "bold");
		assertEquals("2", this.service.getUserNameAtPosition(0));
		assertEquals("1", this.service.getUserNameAtPosition(1));
		this.service.submitWord("4", "glly");
		assertEquals("2", this.service.getUserNameAtPosition(0));
		assertEquals("1", this.service.getUserNameAtPosition(1));
		this.service.submitWord("5", "woolly");
		assertEquals("5", this.service.getUserNameAtPosition(0));
		assertEquals("2", this.service.getUserNameAtPosition(1));
		assertEquals("1", this.service.getUserNameAtPosition(2));
		this.service.submitWord("6", "adder");
		assertEquals("5", this.service.getUserNameAtPosition(0));
		assertEquals("2", this.service.getUserNameAtPosition(1));
		assertEquals("1", this.service.getUserNameAtPosition(2));

		assertEquals("5", this.service.getUserNameAtPosition(0));
		assertEquals("2", this.service.getUserNameAtPosition(1));
		assertEquals("1", this.service.getUserNameAtPosition(2));
		assertEquals(null, this.service.getUserNameAtPosition(3));
		assertEquals(null, this.service.getUserNameAtPosition(4));
		assertEquals(null, this.service.getUserNameAtPosition(5));
		assertEquals(null, this.service.getUserNameAtPosition(6));
		assertEquals(null, this.service.getUserNameAtPosition(7));
		assertEquals(null, this.service.getUserNameAtPosition(8));
		assertEquals(null, this.service.getUserNameAtPosition(9));
	}

	@Test
	public void testLeaderBoardsWords() throws Exception
	{

		this.service.submitWord("1", "no");
		assertEquals("no", this.service.getWordEntryAtPosition(0));
		this.service.submitWord("2", "grow");
		assertEquals("grow", this.service.getWordEntryAtPosition(0));
		assertEquals("no", this.service.getWordEntryAtPosition(1));
		this.service.submitWord("3", "bold");
		assertEquals("grow", this.service.getWordEntryAtPosition(0));
		assertEquals("no", this.service.getWordEntryAtPosition(1));
		this.service.submitWord("4", "glly");
		assertEquals("grow", this.service.getWordEntryAtPosition(0));
		assertEquals("no", this.service.getWordEntryAtPosition(1));
		this.service.submitWord("5", "woolly");
		assertEquals("woolly", this.service.getWordEntryAtPosition(0));
		assertEquals("grow", this.service.getWordEntryAtPosition(1));
		assertEquals("no", this.service.getWordEntryAtPosition(2));
		this.service.submitWord("6", "adder");
		assertEquals("woolly", this.service.getWordEntryAtPosition(0));
		assertEquals("grow", this.service.getWordEntryAtPosition(1));
		assertEquals("no", this.service.getWordEntryAtPosition(2));

		assertEquals("woolly", this.service.getWordEntryAtPosition(0));
		assertEquals("grow", this.service.getWordEntryAtPosition(1));
		assertEquals("no", this.service.getWordEntryAtPosition(2));
		assertEquals(null, this.service.getWordEntryAtPosition(3));
		assertEquals(null, this.service.getWordEntryAtPosition(4));
		assertEquals(null, this.service.getWordEntryAtPosition(5));
		assertEquals(null, this.service.getWordEntryAtPosition(6));
		assertEquals(null, this.service.getWordEntryAtPosition(7));
		assertEquals(null, this.service.getWordEntryAtPosition(8));
		assertEquals(null, this.service.getWordEntryAtPosition(9));
	}

	@Test
	public void testLeaderBoardsDiscardedMultipleSubmissions() throws Exception
	{
		this.service.submitWord("1", "no");
		assertEquals("1", this.service.getUserNameAtPosition(0));
		this.service.submitWord("1", "no");

		assertEquals("1", this.service.getUserNameAtPosition(0));
		assertEquals(null, this.service.getUserNameAtPosition(1));
		assertEquals(null, this.service.getUserNameAtPosition(2));
		assertEquals(null, this.service.getUserNameAtPosition(3));
		assertEquals(null, this.service.getUserNameAtPosition(4));
		assertEquals(null, this.service.getUserNameAtPosition(5));
		assertEquals(null, this.service.getUserNameAtPosition(6));
		assertEquals(null, this.service.getUserNameAtPosition(7));
		assertEquals(null, this.service.getUserNameAtPosition(8));
		assertEquals(null, this.service.getUserNameAtPosition(9));

	}

	@Test
	public void testLeaderBoardsDifferentPlayersSameWord() throws Exception
	{
		this.service.submitWord("1", "no");
		assertEquals("1", this.service.getUserNameAtPosition(0));
		this.service.submitWord("2", "no");
		assertEquals("1", this.service.getUserNameAtPosition(0));
		assertEquals("2", this.service.getUserNameAtPosition(1));
		this.service.submitWord("3", "wooly");
		assertEquals("3", this.service.getUserNameAtPosition(0));
		assertEquals("1", this.service.getUserNameAtPosition(1));
		assertEquals("2", this.service.getUserNameAtPosition(2));

		assertEquals("3", this.service.getUserNameAtPosition(0));
		assertEquals("1", this.service.getUserNameAtPosition(1));
		assertEquals("2", this.service.getUserNameAtPosition(2));
		assertEquals(null, this.service.getUserNameAtPosition(3));
		assertEquals(null, this.service.getUserNameAtPosition(4));
		assertEquals(null, this.service.getUserNameAtPosition(5));
		assertEquals(null, this.service.getUserNameAtPosition(6));
		assertEquals(null, this.service.getUserNameAtPosition(7));
		assertEquals(null, this.service.getUserNameAtPosition(8));
		assertEquals(null, this.service.getUserNameAtPosition(9));

	}

	@Test
	public void testMultithreadSubmission() throws Exception
	{

		Thread t1 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(1000*5);
				}
				catch (InterruptedException e)
				{
					Assert.fail();
				}
				SubmissionTest.this.service.submitWord("otherPlayer", "woolly");
			}
		});

		Thread t2 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				SubmissionTest.this.service.submitWord("2", "grow");
			}
		});

		Thread t3 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				SubmissionTest.this.service.submitWord("3", "bold");
			}
		});

		Thread t4 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				SubmissionTest.this.service.submitWord("5", "woolly");
			}
		});

		Thread t5 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				SubmissionTest.this.service.submitWord("1", "no");
			}
		});


		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();


		t2.join();
		t3.join();
		t4.join();
		t5.join();

		t1.join();

		assertEquals(new Integer(6), this.service.getScoreAtPosition(0));
		assertEquals(new Integer(6), this.service.getScoreAtPosition(1));
		assertEquals(new Integer(4), this.service.getScoreAtPosition(2));
		assertEquals(new Integer(2), this.service.getScoreAtPosition(3));
		assertEquals(null, this.service.getScoreAtPosition(4));
		assertEquals(null, this.service.getScoreAtPosition(5));
		assertEquals(null, this.service.getScoreAtPosition(6));
		assertEquals(null, this.service.getScoreAtPosition(7));
		assertEquals(null, this.service.getScoreAtPosition(8));
		assertEquals(null, this.service.getScoreAtPosition(9));

	}

}
