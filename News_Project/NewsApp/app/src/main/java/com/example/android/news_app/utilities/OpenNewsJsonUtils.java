package com.example.android.news_app.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Vincent on 6/21/2017.
 */

public final class OpenNewsJsonUtils {

    public static String[] getSimpleNewsStringsFromJson(Context context, String newsJsonStr)
            throws JSONException {

        /* News information. */
        final String article = "articles";
        final String authors = "author";
        final String titles = "title";
        final String description = "description";
        final String url = "url";
        final String images = "urlToImage";
        final String published = "publishedAt";

        /* String array to hold each parsed news String */
        String[] parsedNews= null;

        JSONObject jsonNews = new JSONObject(newsJsonStr);

        /* Is there an error ? */
        if (jsonNews.has("status")) {
            String status = jsonNews.getString("status");

            switch(status) {
                case "ok":
                    break;
                default:
                    return null;
            }
        }

        JSONArray arrayOfNews = jsonNews.getJSONArray(article);

        parsedNews = new String[arrayOfNews.length()];

        for(int i = 0; i < arrayOfNews.length(); i++) {
            String news_title;
            String news_description;
            String news_author;
            String news_url;
            String news_image;
            String news_published;

            JSONObject articles = arrayOfNews.getJSONObject(i);

            news_title = articles.getString(titles);
            news_description = articles.getString(description);
            news_author = articles.getString(authors);
            news_url = articles.getString(url);
            news_image = articles.getString(images);
            news_published = articles.getString(published);

            parsedNews[i] = "Title: " + news_title + "\n Author: " + news_author + "\n Description: " + news_description
                    + "\n URL: " + news_url + "\n Image: " + news_image + "\n Published: " + news_published;

        }
        return parsedNews;
    }
}
