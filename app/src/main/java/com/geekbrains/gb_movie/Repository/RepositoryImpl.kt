package com.geekbrains.gb_movie.Repository

import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.Repository.Model.MovieFull
import com.geekbrains.gb_movie.Repository.Model.getMovieFromServer
import com.geekbrains.gb_movie.Repository.Model.getMovieFull

class RepositoryImpl:Repository {
    override fun getMovieFromLocalServer(): List<Movie> = getMovieFromServer()
    override fun getMovieInfoFromLocalServer(): List<MovieFull> = getMovieFull
}