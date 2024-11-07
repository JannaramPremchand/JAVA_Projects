package com.crio.xnews;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;
import java.util.Collections;

public class NewsApiClient {

    private static final String API_URL = "https://newsapi.org/v2/everything";
    private static final String API_KEY = "7ed56c747be74ee2b7ac723dbbf790d3";

// TODO: CRIO_TASK_MODULE_PROJECT
// Utilize the Okhttp3 library to send a request to the News API, including the provided query, language, and sortBy parameters.
// Ensure that the Gradle dependency for Okhttp3 is included in build.gradle.
// Parse the JSON response using NewsParser.
// If the "query" parameter is empty, an IllegalArgumentException is thrown. 
// If there is an error during the API request or response parsing, IOException is thrown.

    public List<NewsArticle> fetchNewsArticles(String query, String language, String sortBy) throws IOException {
        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("Query parameter must not be empty");
        }

        String url = buildUrl(query, language, sortBy);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return NewsParser.parse(response.body().string());
        }
    }

// TODO: CRIO_TASK_MODULE_PROJECT
// Construct the URL required to make a request to the News API and use this in above method.
// Refer to https://newsapi.org/docs/endpoints/everything for guidance on URL construction.
// The "query" parameter is mandatory and must not be empty. 
// If the "query" parameter is empty, throw IllegalArgumentException with message "Query parameter must not be empty".
// The "language" and "sortBy" parameters are optional and will be included in the URL if they are non-empty.

    private String buildUrl(String query, String language, String sortBy) {
        StringBuilder urlBuilder = new StringBuilder(API_URL);
        urlBuilder.append("?q=").append(query);
        urlBuilder.append("&apiKey=").append(API_KEY);

        if (language != null && !language.isEmpty()) {
            urlBuilder.append("&language=").append(language);
        }
        if (sortBy != null && !sortBy.isEmpty()) {
            urlBuilder.append("&sortBy=").append(sortBy);
        }

        return urlBuilder.toString();
    }
}