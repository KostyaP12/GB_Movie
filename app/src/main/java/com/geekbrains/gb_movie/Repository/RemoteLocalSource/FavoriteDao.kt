package com.geekbrains.gb_movie.Repository.RemoteLocalSource

import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM FavoriteEntity")
    fun all(): List<FavoriteEntity>


    @Query("SELECT COUNT (id) FROM FavoriteEntity WHERE id LIKE :id")
    fun find(id: Int): Int

    @Query("DELETE FROM FavoriteEntity WHERE id LIKE :id")
    fun drop(id: Int): Int

    @Query("SELECT * FROM FavoriteEntity WHERE id LIKE :id")
    fun getDataById(id: Int): FavoriteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavoriteEntity)

    @Update
    fun update(entity: FavoriteEntity)

    @Delete
    fun delete(entity: FavoriteEntity)
}