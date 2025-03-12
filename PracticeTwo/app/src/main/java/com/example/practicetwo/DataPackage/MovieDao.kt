package com.example.practicetwo.DataPackage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movies_database")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movies_database WHERE id = :movieId")
    fun getMovieById(movieId: Int): Flow<Movie?>

    @Query("SELECT * FROM movies_database WHERE director = :director")
    fun getMoviesByDirector(director: String): Flow<List<Movie>>

    @Query("SELECT * FROM movies_database WHERE year = :year")
    fun getMoviesByYear(year: Int): Flow<List<Movie>>
}


