package com.geekbrains.gb_movie.Repository.Model

data class Movie(
    val id: Int,
    val original_title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String,
    val title: String
)

fun getMovieFromServer() = arrayListOf<Movie>(
    Movie(1, "Большой Куш", "Заглушка", "Заглушка", "01.01.2001", "Большой Куш"),
    Movie(2, "Большой Куш", "Заглушка", "Заглушка", "01.01.2001", "Большой Куш"),
    Movie(1, "Большой Куш", "Заглушка", "Заглушка", "01.01.2001", "Большой Куш"),
    Movie(1, "Большой Куш", "Заглушка", "Заглушка", "01.01.2001", "Большой Куш"))



