package com.example.practicetwo.DataPackage

import android.content.Context
import com.example.practicetwo.isNetworkAvailable
import com.example.practicetwo.network.MovieDC
import com.example.practicetwo.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


class MovieRepository(private val movieDao: MovieDao,private val context: Context) {

    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies()

    fun getMovieById(movieId: Int): Flow<Movie?> = movieDao.getMovieById(movieId)

    fun getMoviesByDirector(director: String): Flow<List<Movie>> = movieDao.getMoviesByDirector(director)

    fun getMoviesByYear(year: Int): Flow<List<Movie>> = movieDao.getMoviesByYear(year)

    suspend fun insertMovie(movie: Movie) = movieDao.insertMovie(movie)

    suspend fun updateMovie(movie: Movie) = movieDao.updateMovie(movie)

    suspend fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)


    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getRepository(context: Context): MovieRepository {
            return INSTANCE ?: synchronized(this) {
                val database = MovieDatabase.getDatabase(context)
                val instance = MovieRepository(database.movieDao(),context)
                INSTANCE = instance
                instance
            }
        }
    }
}


