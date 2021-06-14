package com.geekbrains.gb_movie.Repository

import com.geekbrains.gb_movie.Repository.Model.MovieFull
import com.geekbrains.gb_movie.Repository.RemoteLocalSource.FavoriteDao
import com.geekbrains.gb_movie.Repository.RemoteLocalSource.FavoriteEntity

class FavoriteRepositoryImpl(private val localDataSource: FavoriteDao) : FavoriteRepository {

    override fun getAllFavorite(): List<MovieFull> {
        return convertFavoriteEntityToMovie(localDataSource.all())
    }

    override fun getFavoriteMovie(id: Int): Int {
        return localDataSource.find(id)
    }

    override fun saveEntity(movie: MovieFull) {
        localDataSource.insert(convertMovieToEntity(movie))
    }

    override fun deleteEntity(id: Int) {
        localDataSource.drop(id)
    }


    private fun convertFavoriteEntityToMovie(entityList: List<FavoriteEntity>): List<MovieFull> {
        return entityList.map {
            MovieFull(
                    it.id,
                    "",
                    it.overview,
                    it.poster_path,
                    it.release_date,
                    it.title,
                    0.0,
                    0,
                    0,
                    0,
                    0,
                    "",
                    0.0
            )
        }
    }


    private fun convertMovieToEntity(movie: MovieFull): FavoriteEntity {
        return FavoriteEntity(movie.id, movie.overview, movie.poster_path, movie.release_date, movie.title)
    }
}