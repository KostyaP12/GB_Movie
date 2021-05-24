package com.geekbrains.gb_movie.Repository

import com.geekbrains.gb_movie.Repository.Model.Movie
import com.geekbrains.gb_movie.Repository.Model.getMovieFromServer

class RepositoryImpl:Repository {
    override fun getMovieFromLocalServer(): List<Movie> = getMovieFromServer()

}