package com.example.netflixtestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.netflixtestapp.databinding.ActivityMainBinding;
import com.example.netflixtestapp.databinding.ActivityMovieDetailBinding;
import com.example.netflixtestapp.models.Movie;
import com.example.netflixtestapp.networking.MovieService;

import org.parceler.Parcels;

public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ivMoviePoster = binding.ivMoviePoster;
        TextView tvTitle = binding.tvTitle;
        TextView tvOverview = binding.tvOverview;


        Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        Glide
                .with(this)
                .load(MovieService.getFullImageUrl(movie.getBackdropPath()))
                .into(ivMoviePoster);

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}