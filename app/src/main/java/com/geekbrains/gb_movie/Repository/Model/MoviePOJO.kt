package com.geekbrains.gb_movie.Repository.Model

data class MoviePOJO (
    val page: Int,
    val total_pages: Int,
    val result: ArrayList<Movie>
)
