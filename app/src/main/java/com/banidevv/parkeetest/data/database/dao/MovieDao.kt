package com.banidevv.parkeetest.data.database.dao

import androidx.room.*
import com.banidevv.parkeetest.data.database.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MovieEntity)

    @Delete
    suspend fun delete(entity: MovieEntity)

    @Query("SELECT * FROM movie WHERE id = :movieId")
    suspend fun fetchById(movieId: Int): MovieEntity?

    @Query("SELECT * FROM movie")
    suspend fun fetchAll(): List<MovieEntity>
}