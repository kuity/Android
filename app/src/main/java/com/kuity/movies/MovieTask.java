package com.kuity.movies;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lingzhang on 2/14/2016.
 */

public class MovieTask {
    String BaseURL = "https://api.themoviedb.org/3/discover/movie?api_key=";
    String apiKey = "";
    private static final String LOG_TAG = MovieTask.class.getSimpleName();
    private static String basePosterURL = "http://image.tmdb.org/t/p/w185/";

    public List readIntArray(JsonReader reader) throws IOException {
        List ints = new ArrayList();
        reader.beginArray();
        while (reader.hasNext()) {
            ints.add(reader.nextInt());
        }
        reader.endArray();
        return ints;
    }

    public Movie readMovieObject(JsonReader reader) throws IOException {
        Movie movie = new Movie();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (reader.peek() == JsonToken.NULL){
                reader.skipValue();
                continue;
            }
            switch (name) {
                case "poster_path":
                    movie.poster = basePosterURL + reader.nextString();
                    break;
                case "overview":
                    movie.overview = reader.nextString();
                    break;
                case "release_date":
                    movie.release_date = reader.nextString();
                    break;
                case "genre_ids":
                    movie.genre_ids = readIntArray(reader);
                    break;
                case "title":
                    movie.title = reader.nextString();
                    break;
                case "original_language":
                    movie.language = reader.nextString();
                    break;
                case "popularity":
                    movie.popularity = reader.nextDouble();
                    break;
                case "vote_count":
                    movie.vote_count = reader.nextDouble();
                    break;
                case "vote_average":
                    movie.vote_average = reader.nextDouble();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return movie;
    }

    public ArrayList<Movie> getMovies(String s) {
        String path = BaseURL + apiKey;

        if (s.equals("Most popular")) {
            path = BaseURL + apiKey + "&sort_by=popularity.desc";
            Log.d(LOG_TAG, path);
        } else if (s.equals("Highest rated")) {
            path = BaseURL + apiKey + "&sort_by=vote_average.desc";
            Log.d(LOG_TAG, path);
        }

        URL url;
        HttpURLConnection urlConnection = null;
        ArrayList<Movie> movielist = new ArrayList<Movie>();

        try {
            url = new URL(path);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("results")) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        movielist.add(readMovieObject(reader));
                    }
                    reader.endArray();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            Log.d(LOG_TAG, "Attempting to retrieve movies...");
            Log.d(LOG_TAG, Arrays.toString(movielist.toArray()));
            return movielist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
