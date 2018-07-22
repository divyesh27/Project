package com.qa.api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.testng.Assert;

/**
 * This test class will validate all the business requirements listed in the
 * requirements
 * 
 * @author divyesh.shah
 *
 */
public class SplunkClientTest {
	private SplunkClient client;
	private List<MovieAttributes> movies;

	/**
	 * This is the setup method to initialize Client and get all the movies list
	 * 
	 * @throws Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		client = new SplunkClient();
		movies = client.listMovies("batman").getResults();
	}

	/**
	 * Tear down and set the client and movies to null
	 * 
	 * @throws Exception
	 */
	@AfterMethod
	public void tearDown() throws Exception {
		client = null;
		movies = null;

	}

	/**
	 * This test will particularly validate there are no two movies having the same
	 * poster path
	 */
	@Test
	public void testSpl001() {
		Set<String> posterPaths = new HashSet<>();
		for (MovieAttributes movie : movies) {
			Assert.assertTrue(posterPaths.add(movie.getPosterPath()));
		}
	}

	/**
	 * This test will validate all poster path to be valid. Poster path to be valid
	 * is decided based on the url starting with https://. Also it validates if
	 * poster path is null.
	 */
	@Test
	public void testSpl002() {
		for (MovieAttributes movie : movies) {
			try {
				Assert.assertTrue(movie.getPosterPath().startsWith("https://"), "Path not found with https://");
			} catch (Exception e) {
				Assert.assertNull(movie.getPosterPath());
			}
		}
	}

	/**
	 * This test is to validate if movies are sorted by ids. If genre ids are found
	 * null then that should be first in response even genre ids are returned null
	 * for more than 1 movies then it has to sort by ids and return.
	 */
	@Test
	public void testSpl003() {
		movies.sort(Comparator.comparingInt(MovieAttributes::getId));
		Collections.sort(movies);
		for (int i = 1; i < movies.size(); i++) {
			MovieAttributes pre = movies.get(i - 1);

			MovieAttributes cur = movies.get(i);
			if (pre.getGenreIds().isEmpty() && !cur.getGenreIds().isEmpty()) {
				continue;
			}
			Assert.assertTrue(pre.getId() < cur.getId());
			Assert.assertFalse(!pre.getGenreIds().isEmpty() && cur.getGenreIds().isEmpty());
		}
	}

	/**
	 * Test is to validate no more than 7 movies should be returned whose genre ids
	 * sum is greater than 400.
	 */
	@Test
	public void testSpl004() {
		long count = movies.stream().map(movie -> movie.getGenreIds().stream().mapToInt(i -> i.intValue()).sum())
				.filter(sum -> sum > 400).count();
		Assert.assertTrue(count < 8);
	}

	/**
	 * This test is to validate at least one movie whose title contains a palindrome
	 * in it.
	 */
	@Test
	public void testSpl005() {
		int count = 0;
		for (MovieAttributes movie : movies) {
			String[] titles = movie.getTitle().split(" ");
			for (String t : titles) {
				if (isPalindrome(t)) {
					count++;
				}
			}
		}
		Assert.assertTrue(count >= 1);

	}

	/**
	 * This test validates at least two movies whose title contains the title of
	 * another movies
	 */
	@Test
	public void testSpl006() {
		int count = 0;
		List<String> titles = movies.stream().map(MovieAttributes::getTitle).collect(Collectors.toList());

		for (int i = 0; i < titles.size(); i++) {
			for (int j = 1; j < titles.size(); j++) {
				if (titles.get(j).contains(titles.get(i))) {
					count++;
				}
			}
		}
		Assert.assertTrue(count >= 2);
	}

	/**
	 * This is a private method to validate given string to be palindrome or not.
	 * 
	 * @param title
	 * @return boolean
	 */
	private boolean isPalindrome(String title) {
		int start = 0;
		int end = title.length() - 1;
		while (start < end) {
			if (title.charAt(start++) != title.charAt(end--)) {
				return false;
			}
		}
		return true;
	}

}
