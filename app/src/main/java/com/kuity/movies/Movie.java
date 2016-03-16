package com.kuity.movies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lingzhang on 2/25/2016.
 */
public class Movie implements Parcelable {
    public String poster;
    public String overview;
    public String release_date;
    public List genre_ids;
    public String title;
    public String language;
    public Double popularity;
    public Double vote_count;
    public Double vote_average;

    public String toString() {
        return "Poster: " + this.poster + "overview: " + this.overview +
                "release date: " + this.release_date + "title: " + this.title;
    }

    public Movie() {
        this.genre_ids = new ArrayList<Integer>();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(poster);
        out.writeString(overview);
        out.writeString(release_date);
        out.writeList(genre_ids);
        out.writeString(title);
        out.writeString(language);
        out.writeDouble(popularity);
        out.writeDouble(vote_count);
        out.writeDouble(vote_average);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in){
        this.genre_ids = new ArrayList<Integer>();
        this.poster = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        in.readList(this.genre_ids, null);
        this.title = in.readString();
        this.language = in.readString();
        this.popularity = in.readDouble();
        this.vote_count = in.readDouble();
        this.vote_average = in.readDouble();
    }
}