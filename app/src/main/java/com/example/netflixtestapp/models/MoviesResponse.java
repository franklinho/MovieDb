package com.example.netflixtestapp.models;

import java.util.List;

public class MoviesResponse {
    private int page;
    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }
}
