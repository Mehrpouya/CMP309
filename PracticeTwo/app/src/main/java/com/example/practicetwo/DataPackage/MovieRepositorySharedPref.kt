package com.example.practicetwo.DataPackage

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.add
import androidx.compose.ui.input.key.type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MovieRepositorySharedPref(private val context: Context) {
    // MODE_PRIVATE is the default and most common mode.
    // It means that the SharedPreferences file you create will only be accessible by your app.
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getAllMovies(): Flow<List<Movie>> = flow {
        val moviesList = getMoviesFromSharedPreferences()
        emit(moviesList)
    }

    suspend fun insertMovie(movie: Movie) {
        val currentMovies = getMoviesFromSharedPreferences().toMutableList()
        currentMovies.add(movie)
        saveMoviesToSharedPreferences(currentMovies)
    }

    private fun getMoviesFromSharedPreferences(): List<Movie> {
        val json = sharedPreferences.getString("movies", null)
        return if (json != null) {
            // Convert the JSON string back to a list of Movie objects
            // Java and Kotlin have a concept called type erasure.
            // hence we use TypeToken to specify the type of the list
            val type = object : TypeToken<List<Movie>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } else {
            emptyList()
        }
    }
    private fun saveMoviesToSharedPreferences(moviesList: List<Movie>) {
        val json = gson.toJson(moviesList)
        sharedPreferences.edit().putString("movies", json).apply()
    }
    //Creates a place to hold "static-like" members (like INSTANCE and getRepository()).
    companion object {
        //Makes sure that the INSTANCE variable is always up-to-date across threads.
        @Volatile
        private var INSTANCE: MovieRepositorySharedPref? = null

        fun getRepository(context: Context): MovieRepositorySharedPref{
            return INSTANCE ?: synchronized(this) {
                val instance = MovieRepositorySharedPref(context)
                INSTANCE = instance
                instance
            }
        }
    }
}


