package com.example.netflixtestapp.networking;

import com.example.netflixtestapp.models.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    //TODO: Add api parameters to support pagination

    @GET("3/trending/movie/week")
    Call<MoviesResponse> getTrendingMovies();

    @GET("3/search/movie")
    Call<MoviesResponse> searchMovies(@Query("query") String query);
}
