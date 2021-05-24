package com.geekbrains.gb_movie.Repository.Model

data class MovieFull(
        val id: Int,
        val original_title: String,
        val overview: String?,
        val poster_path: String?,
        val release_date: String,
        val title: String,
        val vote_average: Double,
        val vote_count: Int,
        val budget: Int,
        val revenue: Int,
        val runtime: Int?,
        val status: String,
        val popularity: Double
)
val movieFull = listOf<MovieFull>(MovieFull(1, "Большой куш", "Большой куш", "Большой куш", "Большой куш", "Большой куш", 15.04, 15, 15, 15, 15, "Большой куш", 15.04))
