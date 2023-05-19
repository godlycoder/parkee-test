package com.banidevv.parkeetest.data.datasources.movie

import com.banidevv.parkeetest.data.database.AppDatabase
import com.banidevv.parkeetest.data.database.dao.MovieDao
import com.banidevv.parkeetest.data.database.entity.MovieEntity
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun addToFavorite(entity: MovieEntity) {
        movieDao.insert(entity)
    }

    suspend fun removeFromFavorite(entity: MovieEntity) {
        movieDao.delete(entity)
    }

    suspend fun checkIsFavorite(movieId: Int): Boolean {
        return movieDao.fetchById(movieId) != null
    }

    suspend fun getFavoriteMovies(): List<MovieEntity> {
        return movieDao.fetchAll()
    }

}
