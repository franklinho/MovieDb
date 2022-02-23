package com.example.netflixtestapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM MovieData where movieId := :id")
    public MovieData getById(int id);

    @Query("Select * FROM MovieData")
    public List<MovieData> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertMovie(MovieData movie);

    @Delete
    public void deleteMovie(MovieData movie);
}
