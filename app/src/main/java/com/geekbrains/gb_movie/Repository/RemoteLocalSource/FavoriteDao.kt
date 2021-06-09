package com.geekbrains.gb_movie.Repository.RemoteLocalSource

import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM FavoriteEntity")
    fun all(): List<FavoriteEntity>

    @Query("SELECT * FROM FavoriteEntity WHERE title LIKE :title")
    fun getDataByWord(title: String): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FavoriteEntity)

    @Update
    fun update(entity: FavoriteEntity)

    @Delete
    fun delete(entity: FavoriteEntity)

}