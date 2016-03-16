package com.kuity.movies;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import com.kuity.movies.adapters.MovieListAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBarToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Recent Movie Lister");

        Integer pos = 0;
        Bundle spinnerInfo = getIntent().getExtras();
        if (spinnerInfo != null) {
            pos = spinnerInfo.getInt("spinnerpos");
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setSelection(pos);
        final String sortBy = String.valueOf(spinner.getSelectedItem());
        spinner.setSelection(pos, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                restartActivity(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        final MovieListAdapter movieListAdapter = new MovieListAdapter(this, new ArrayList<Movie>());
        GridView movieListView = (GridView) findViewById(R.id.movies);
        movieListView.setAdapter(movieListAdapter);

        getLoaderManager().initLoader(0, savedInstanceState,
            new LoaderManager.LoaderCallbacks<ArrayList<Movie>>() {
                @Override
                public Loader<ArrayList<Movie>> onCreateLoader(int id, Bundle args) {
                    return new MovieLoader(MainActivity.this, sortBy);
                }
                @Override
                public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {
                    movieListAdapter.setData(data);
                }
                @Override
                public void onLoaderReset(Loader<ArrayList<Movie>> loader) {
                    movieListAdapter.setData(new ArrayList<Movie>());
                }
            }
        ).forceLoad();

        //Picasso.with(this).load("");
    }

    private void restartActivity(int pos) {
        Intent intent = getIntent();
        intent.putExtra("spinnerpos", pos);
        finish();
        startActivity(intent);
    }


}
