package com.geekbrains.gb_movie.Repository

import com.geekbrains.gb_movie.Repository.Model.Movie

interface Repository {
    fun getMovieFromLocalServer(): ArrayList<Movie>
}