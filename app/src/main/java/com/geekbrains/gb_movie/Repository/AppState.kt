package com.geekbrains.gb_movie.Repository

import com.geekbrains.gb_movie.Repository.Model.MovieFull

sealed class AppState {
    data class Success(val movieData: List<MovieFull>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}