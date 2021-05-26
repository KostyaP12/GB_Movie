package com.geekbrains.gb_movie.Repository.Model

data class Movie(
    val adult: Boolean,
    val id: Int,
    val original_title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val original_language: String,
    val popularity: Int,
    val vote_average: Double,
    val vote_count: Int
)

fun getMovieFromServer() = listOf(
    Movie(false,1, "Большой Куш", "Заглушка", "Заглушка", "01.01.2001", "Большой Куш", "EN",15, 1.1, 200),
    Movie(false,2, "Большой Куш", "Заглушка", "Заглушка", "01.01.2001", "Большой Куш","EN",15,1.1,200),
    Movie(false,3, "Большой Куш", "Заглушка", "Заглушка", "01.01.2001", "Большой Куш","EN",15,1.1,200),
    Movie(false,4, "Большой Куш", "Заглушка", "Заглушка", "01.01.2001", "Большой Куш","EN",15,1.1,200))



