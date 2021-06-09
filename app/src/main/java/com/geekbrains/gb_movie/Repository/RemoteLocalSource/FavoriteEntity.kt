package com.geekbrains.gb_movie.Repository.RemoteLocalSource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
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