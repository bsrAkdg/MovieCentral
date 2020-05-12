package com.bsrakdg.moviecentral.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private int id;
    private double popularity;
    private int vote_count;
    private double vote_average;
    private String title;
    private String release_date;
    private String poster_path;
    private String overview;

    public Movie(int id, double popularity, int vote_count, double vote_average, String title,
                 String release_date, String poster_path, String overview) {
        this.id = id;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.title = title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.overview = overview;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        popularity = in.readDouble();
        vote_count = in.readInt();
        vote_average = in.readDouble();
        title = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        overview = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeDouble(popularity);
        parcel.writeInt(vote_count);
        parcel.writeDouble(vote_average);
        parcel.writeString(title);
        parcel.writeString(release_date);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
