package com.example.netflixtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.example.netflixtestapp.databinding.ActivityMainBinding;
import com.example.netflixtestapp.models.Movie;
import com.example.netflixtestapp.viewmodels.MoviesViewModel;

import java.lang.annotation.Annotation;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvData;
    EditText etSearch;
    MoviesRecyclerViewAdapter adapter;
    private ActivityMainBinding binding;
    MoviesViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        rvData = binding.rvRecycler;
        viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        viewModel.getMoviesLiveData().observe(this, dataItemListUpdateObserver);

        adapter = new MoviesRecyclerViewAdapter(new MovieViewHolderListener() {
            @Override
            public void onItemClicked(Movie movie) {
                viewModel.launchMovieActivity(MainActivity.this, movie);
            }
        });
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new GridLayoutManager(this, 3));

        etSearch = binding.etSearch;
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String query = textView.getText().toString();
                if (!query.isEmpty()) {
                    viewModel.searchMovies(query);
                } else {
                    viewModel.requestTrendingMovies();
                }
                return false;
            }
        });
    }

    Observer<List<Movie>> dataItemListUpdateObserver = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> movies) {
            adapter.setItems(movies);
        }
    };
}