package com.geekbrains.gb_movie.Repository

import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.Repository.Model.MovieFull

interface Repository {
    fun getMovieFromLocalServer(): List<Movie>
    fun getMovieInfoFromLocalServer(): List<MovieFull>
}