package com.example.netflixtestapp.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.netflixtestapp.MovieDetailActivity;
import com.example.netflixtestapp.models.Movie;
import com.example.netflixtestapp.models.MoviesResponse;
import com.example.netflixtestapp.networking.MovieApi;
import com.example.netflixtestapp.networking.MovieService;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends ViewModel {
    private MovieService movieService = new MovieService();
    private MovieApi movieApi = movieService.getMovieApi();
    private MutableLiveData<List<Movie>> moviesLiveData;
    private  ArrayList<Movie> movies = new ArrayList<>();

    public MoviesViewModel() {
        moviesLiveData = new MutableLiveData<>();
        requestTrendingMovies();
    }

    public void requestTrendingMovies() {
        //TODO: Cache trending movies for 24 hours
        movieApi.getTrendingMovies().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movieList = response.body().getResults();
                    movies.addAll(movieList);
                    moviesLiveData.setValue(movieList);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("MovieService/TrendingApi", t.getLocalizedMessage());
            }
        });
    }

    public void searchMovies(String query) {
        movieApi.searchMovies(query).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movieList = response.body().getResults();
                    movies.addAll(movieList);
                    moviesLiveData.setValue(movieList);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("MovieService/SearchAPI", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void launchMovieActivity(Activity activity, Movie movie) {
        //TODO: Add shared element transition
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra("movie", Parcels.wrap(movie));
        activity.startActivity(intent);
    }
}
