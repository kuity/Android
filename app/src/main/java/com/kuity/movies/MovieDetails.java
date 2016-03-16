package com.kuity.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class MovieDetails extends AppCompatActivity {
    private static final String LOG_TAG = MovieDetails.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mtitle = (TextView) findViewById(R.id.mtitle);
        TextView mdesc = (TextView) findViewById(R.id.mdesc);
        TextView mrating = (TextView) findViewById(R.id.mrating);
        TextView mvotes = (TextView) findViewById(R.id.mvotes);
        Movie movie = getIntent().getParcelableExtra("movie");
        Log.d(LOG_TAG, movie.toString());
        mtitle.setText(movie.title);
        mdesc.setText(movie.overview);
        mrating.setText("Average rating: " + String.valueOf(movie.vote_average) + "/10");
        mvotes.setText("Number of votes: " + String.valueOf(movie.vote_count));
    }

}
