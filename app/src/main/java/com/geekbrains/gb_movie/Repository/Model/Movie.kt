package com.geekbrains.gb_movie.Repository.Model

data class Movie(
    val id: Int,
    val original_title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String,
    val title: String
)