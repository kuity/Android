package com.kuity.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kuity.movies.Movie;
import com.kuity.movies.MovieDetails;
import com.kuity.movies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lingzhang on 3/5/2016.
 */
public class MovieListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Movie> movieList = new ArrayList<Movie>();
    private Context context;

    public MovieListAdapter(Context context, ArrayList<Movie> movielist) {
        this.movieList = movielist;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Movie> data) {
        if (movieList != null) {
            movieList.clear();
        } else {
            movieList =new ArrayList<Movie>();
        }
        if (data != null) {
            movieList.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Movie movie = (Movie) getItem(position);
        if (view == null) {
            view = inflater.inflate(R.layout.movieposter, null);
        }
        ImageView poster = (ImageView) view.findViewById(R.id.poster);
        Picasso.with(parent.getContext()).load(movie.poster).into(poster);
        poster.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("movie", movie);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
