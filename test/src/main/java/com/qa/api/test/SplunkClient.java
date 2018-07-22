package com.qa.api.test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This is Splunk Client to call rest API and retrieve all movies list.
 * 
 * @author divyesh.shah
 *
 */
public class SplunkClient {

	private static OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final String BASE_URL = "https://splunk.mocklab.io/movies";
	private static final Gson gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

	/**
	 * This will request all movies list as a MovieCollection and will parse the
	 * JSON response to the Movie Collection
	 * 
	 * @param name
	 *            It takes the input parameter as a name and find all movies
	 *            comtains the name
	 * @return It returns list of movies.
	 * 
	 * @throws Exception
	 */
	public MovieCollection listMovies(String name) throws Exception {

		Request request = new Request.Builder().url(BASE_URL + "?q=" + name).addHeader("Accept", "application/json")
				.get().build();
		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected code " + response);
			}
			String body = response.body().string();
			return gson.fromJson(body, MovieCollection.class);
		}
	}
}
