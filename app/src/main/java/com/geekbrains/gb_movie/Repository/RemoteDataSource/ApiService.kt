package com.geekbrains.gb_movie.Repository.RemoteDataSource

import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.Repository.Model.MoviePOJO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(value = Constants.popular_movies)
    fun getPopular(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<MoviePOJO>

    @GET(value = Constants.now_playing_movies)
    fun getLookNow(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<MoviePOJO>

    @GET(value = Constants.upcoming_movies)
    fun getUpComing(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<MoviePOJO>
}