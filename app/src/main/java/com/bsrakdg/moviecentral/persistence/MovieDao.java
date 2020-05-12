package com.bsrakdg.moviecentral.persistence;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bsrakdg.moviecentral.models.Genre;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM genres")
    LiveData<List<Genre>> getGenres();

    // no conflict return = { id1, id2, id3, ...}
    // no conflict return = { -1, id2, -1, ...} id1 and id3 conflict
    @Insert(onConflict = REPLACE)
    long[] saveGenres(List<Genre> genres);
}
