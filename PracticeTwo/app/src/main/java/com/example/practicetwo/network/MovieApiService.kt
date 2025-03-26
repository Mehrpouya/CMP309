package com.example.practicetwo.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path


interface MovieApiService {
    @GET("search/movie")
    fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Call<MoviePage>

    @GET("movie/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movieId: Int
    ): Call<MovieDC>
}
