package com.qa.api.test;

import java.util.List;

/**
 * This POJO is to maintain list of Movie attributes and also implements
 * Comparable for using Comparator to validate test case
 * 
 * @author divyesh.shah
 *
 */
public class MovieAttributes implements Comparable<MovieAttributes> {

	private int voteCount;
	private int id;
	private boolean video;
	private float voteAverage;
	private String title;
	private double popularity;
	private String originalLanguage;
	private String originalTitle;
	private List<Integer> genreIds;
	private boolean adult;
	private String overview;
	private String releaseDate;
	private String posterPath;

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVideo() {
		return video;
	}

	public void setVideo(boolean video) {
		this.video = video;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(float voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * This will override compare and validate with genre id if it is Empty or not by comparing.
	 */
	@Override
	public int compareTo(MovieAttributes o) {
		// return this.getId() - o.getId();
		if (this.getGenreIds().isEmpty() && !o.getGenreIds().isEmpty()) {
			return -1;
		} else if (!this.getGenreIds().isEmpty() && o.getGenreIds().isEmpty()) {
			return 1;
		} else {
			return 0;
		}
	}
}
