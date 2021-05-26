package com.geekbrains.gb_movie

class Constants {
    companion object {
        const val API_KEY = "f163c46b4a24e208bc4bcc86d267e255"
        const val BUNDLE_MOVIE_ID = "movie_id"
        const val basicURL = "https://api.themoviedb.org"
        const val popular_movies = "/3/movie/popular"
        const val now_playing_movies = "/3/movie/now_playing"
        const val upcoming_movies = "/3/movie/upcoming"

        val locale: String = java.util.Locale.getDefault().language

    }
}