package com.geekbrains.gb_movie.Repository.Model

import java.io.Serializable

data class MovieResponse (
    val page: Int,
    val total_pages: Int,
    val results: ArrayList<Movie>
) : Serializable