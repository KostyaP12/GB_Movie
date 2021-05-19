package com.geekbrains.gb_movie.Repository

import com.geekbrains.gb_movie.Repository.Model.Movie

sealed class AppState {
    data class Success(val movieData: ArrayList<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}