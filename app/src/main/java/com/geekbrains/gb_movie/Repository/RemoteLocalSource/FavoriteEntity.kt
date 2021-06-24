package com.geekbrains.gb_movie.Repository.RemoteLocalSource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    val overview: String?,
    val poster_path: String?,
    val release_date: String,
    val title: String
)