package com.example.netflixtestapp;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.netflixtestapp.databinding.MovieRecyclerviewItemBinding;
import com.example.netflixtestapp.models.Movie;
import com.example.netflixtestapp.networking.MovieService;

import java.util.ArrayList;
import java.util.List;

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder> {
    DiffUtil.ItemCallback<Movie> diffUtilCallback = new DiffCallback();
    private AsyncListDiffer<Movie> mAsyncListDiffer = new AsyncListDiffer<Movie>(this, diffUtilCallback);
    private MovieViewHolderListener movieViewHolderListener;

    public MoviesRecyclerViewAdapter(MovieViewHolderListener listener) {
        movieViewHolderListener = listener;
    }

    public void setItems(List<Movie> items) {
        mAsyncListDiffer.submitList(items);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        MovieRecyclerviewItemBinding binding = MovieRecyclerviewItemBinding.inflate(inflater);
        View view = binding.getRoot();
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mAsyncListDiffer.getCurrentList().get(position);
        holder.itemView.setOnClickListener(view -> movieViewHolderListener.onItemClicked(movie));
        Glide
                .with(holder.ivMoviePoster.getContext())
                .load(MovieService.getFullImageUrl(movie.getPosterPath()))
                .into(holder.ivMoviePoster);
    }

    @Override
    public int getItemCount() {
        return mAsyncListDiffer.getCurrentList().size();
    }

    @Override
    public void onViewRecycled(@NonNull MovieViewHolder holder) {
        super.onViewRecycled(holder);
        holder.ivMoviePoster.setImageURI(null);
    }

    //TODO: Add pagination

    private class DiffCallback extends DiffUtil.ItemCallback<Movie> {
        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivMoviePoster;
        public View itemView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivMoviePoster = (ImageView) itemView.findViewById(R.id.ivMoviePoster);
        }
    }
}