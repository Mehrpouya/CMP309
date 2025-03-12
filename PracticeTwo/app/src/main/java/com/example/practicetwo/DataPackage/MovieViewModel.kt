package com.example.practicetwo.DataPackage

import android.app.Application
import androidx.activity.result.launch
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository = MovieRepository.getRepository(application)

    val allMovies: Flow<List<Movie>> = repository.getAllMovies()

    fun insertMovie(movie: Movie) {
        viewModelScope.launch {
            repository.insertMovie(movie)
        }
    }

    fun updateMovie(movie: Movie) {
        viewModelScope.launch {
            repository.updateMovie(movie)
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }

    fun getMovieById(movieId: Int): Flow<Movie?> {
        return repository.getMovieById(movieId)
    }

    fun getMoviesByDirector(director: String): Flow<List<Movie>> {
        return repository.getMoviesByDirector(director)
    }

    fun getMoviesByYear(year: Int): Flow<List<Movie>> {
        return repository.getMoviesByYear(year)
    }
}