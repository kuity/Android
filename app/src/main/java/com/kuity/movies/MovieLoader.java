package com.kuity.movies;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Lingzhang on 3/6/2016.
 */
public class MovieLoader extends AsyncTaskLoader<ArrayList<Movie>> {
    private MovieTask movieTask = new MovieTask();
    private String sortBy;

    public MovieLoader(Context context, String s) {
        super(context);
        sortBy = s;
    }

    @Override
    public ArrayList<Movie> loadInBackground() {
        return movieTask.getMovies(sortBy);
    }
}
