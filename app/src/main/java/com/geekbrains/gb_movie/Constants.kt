package com.geekbrains.gb_movie

import android.annotation.SuppressLint

object Constants {
    const val SP_ADULT_KEY = "SP_ADULT_KEY"
    const val API_KEY = "f163c46b4a24e208bc4bcc86d267e255"
    const val BUNDLE_MOVIE_ID = "movie_id"
    const val basicURL = "https://api.themoviedb.org"
    const val popular_movies = "/3/movie/popular"
    const val now_playing_movies = "/3/movie/now_playing"
    const val upcoming_movies = "/3/movie/upcoming"
    const val top_rating = "/3/movie/top_rated"
    const val API_IMAGE_URL = "https://image.tmdb.org/t/p/w400"

    @SuppressLint("ConstantLocale")
    val LOCALE: String = java.util.Locale.getDefault().language

}
