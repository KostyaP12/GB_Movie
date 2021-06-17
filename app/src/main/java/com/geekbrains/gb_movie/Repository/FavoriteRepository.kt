package com.geekbrains.gb_movie.Repository

import com.geekbrains.gb_movie.Repository.Model.MovieFull

interface FavoriteRepository {
    fun getAllFavorite(): List<MovieFull>
    fun getFavoriteMovie(id: Int): Int
    fun saveEntity(movie: MovieFull)
    fun deleteEntity(id: Int)
}