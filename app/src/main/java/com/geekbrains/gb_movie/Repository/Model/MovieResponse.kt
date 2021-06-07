package com.geekbrains.gb_movie.Repository.Model

data class MovieResponse (
    val page: Int,
    val total_pages: Int,
    val results: ArrayList<Movie>
)