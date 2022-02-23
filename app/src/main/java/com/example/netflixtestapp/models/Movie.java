package com.example.netflixtestapp.models;

import org.parceler.Parcel;

@Parcel
public class Movie {
    private int id;
    private String title;
    private boolean adult;
    private String poster_path;
    private String overview;
    private String backdrop_path;

    public String getTitle() {
        return title;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }
}
