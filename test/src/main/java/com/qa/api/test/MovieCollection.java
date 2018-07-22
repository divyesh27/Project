package com.qa.api.test;

import java.util.List;

/**
 * POJO to maintain Movie Collection for the return results with all movie
 * attributes
 * 
 * @author divyesh.shah
 *
 */
public class MovieCollection {

	private List<MovieAttributes> results;

	public List<MovieAttributes> getResults() {
		return results;
	}

	public void setResults(List<MovieAttributes> results) {
		this.results = results;
	}
}
