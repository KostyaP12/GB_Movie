package com.geekbrains.gb_movie.Repository.RemoteDataSource

import com.geekbrains.gb_movie.Constants
import com.geekbrains.gb_movie.Repository.Model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.popular_movies)
    fun getPopular(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ):Call<MovieResponse>

    @GET(Constants.now_playing_movies)
    fun getLookNow(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ):Call<MovieResponse>

    @GET(Constants.upcoming_movies)
    fun getUpComing(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ):Call<MovieResponse>

    @GET(Constants.top_rating)
    fun getTopRating(
            @Query("api_key") key: String,
            @Query("language") lang: String
    ):Call<MovieResponse>
}