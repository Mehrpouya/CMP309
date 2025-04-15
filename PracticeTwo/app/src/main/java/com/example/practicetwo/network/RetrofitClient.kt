package com.example.practicetwo.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    //Ensures link is only accessible within the client object.
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    // we want to add API key and other details to our okhttp client.
    // We use the addinterceptor to modify API request before/after API calls.
    // below we add API key to our request
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()

            val newRequest = originalRequest.newBuilder()
                .header("accept", "application/json")
                .header(
                    "Authorization",
                    "Bearer <APIKEY> "
                )
                .build()

            chain.proceed(newRequest)
        }
        .build()

    val instance: MovieApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        // Create the network class that communicated with API based on the interface, base URL and extra authorisation settings we added.
        retrofit.create(MovieApiService::class.java)
    }
}
