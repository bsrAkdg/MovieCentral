package com.bsrakdg.moviecentral.persistence;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.models.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM genres")
    LiveData<List<Genre>> getGenres();

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    LiveData<Movie> getMovieDetail(int id);

    // no conflict return = { id1, id2, id3, ...}
    // no conflict return = { -1, id2, -1, ...} id1 and id3 conflict
    @Insert(onConflict = REPLACE)
    long[] saveGenres(List<Genre> genres);

    @Insert(onConflict = REPLACE)
    long[] saveMovies(List<Movie> movies);

    @Insert(onConflict = REPLACE)
    long saveMovie(Movie movie);

    @Query("UPDATE genres SET name = :name, date = :date WHERE  id = :id")
    void updateGenre(int id, String name, int date);

    @Query("UPDATE movies SET popularity = :popularity, vote_count = :vote_count, "
            + "vote_average = :vote_average, title = :title, release_date = :release_date, "
            + "poster_path = :poster_path, overview = :overview WHERE  id = :id")
    void updateMovie(int id, double popularity, int vote_count, double vote_average, String title,
                     String release_date, String poster_path, String overview);
}
