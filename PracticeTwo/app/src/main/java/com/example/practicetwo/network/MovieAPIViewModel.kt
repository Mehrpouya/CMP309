package com.example.practicetwo.network


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class MovieAPIViewModel : ViewModel() {
    private val movieApiService = RetrofitClient.instance
    var movies by mutableStateOf<List<MovieDC>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set
    var selectedMovie by mutableStateOf<MovieDC?>(null)
        private set
    fun searchMovies(query: String, page: Int = 1) {
        if (query.isBlank()) {
            movies = emptyList()
            return
        }
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            val call = movieApiService.searchMovies(query, page)
            call.enqueue(object : Callback<MoviePage> {
                override fun onResponse(call: Call<MoviePage>, response: Response<MoviePage>) {
                    isLoading = false
                    if (response.isSuccessful) {
                        val moviePage = response.body()
                        movies = moviePage?.movies ?: emptyList()
                    } else {
                        /*
                        TODO: once you've learned the code better and added your own API, try to add better error handling here.
                    */
                        errorMessage = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<MoviePage>, t: Throwable) {
                    isLoading = false
                    errorMessage = "Failure: ${t.message}"
                }
            })
        }
    }
    fun getMovieById(movieId: Int) {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            val call = movieApiService.getMovieById(movieId)
            call.enqueue(object : Callback<MovieDC> {
                override fun onResponse(call: Call<MovieDC>, response: Response<MovieDC>) {
                    isLoading = false
                    if (response.isSuccessful) {
                        selectedMovie = response.body()
                    } else {
                        errorMessage = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<MovieDC>, t: Throwable) {
                    isLoading = false
                    errorMessage = "Failure: ${t.message}"
                }
            })
        }
    }
}