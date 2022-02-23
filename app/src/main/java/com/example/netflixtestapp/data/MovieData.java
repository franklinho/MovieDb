package com.example.netflixtestapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity
public class MovieData {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    Long id;

    @ColumnInfo
    int movieId;

    @ColumnInfo
    String title;

    @ColumnInfo
    String poster_path;

    @ColumnInfo
    String backdrop_path;
}

