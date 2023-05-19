package com.banidevv.parkeetest.domain.repository

import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<Result<List<MovieUiModel>>>
    suspend fun getTopRatedMovies(): Flow<Result<List<MovieUiModel>>>
    suspend fun getNowPlayingMovies(): Flow<Result<List<MovieUiModel>>>
    suspend fun addToFavorite(movieUiModel: MovieUiModel): Flow<Result<Boolean>>
    suspend fun removeFromFavorite(movieUiModel: MovieUiModel): Flow<Result<Boolean>>
    suspend fun checkIsFavorite(movieId: Int): Flow<Result<Boolean>>
    suspend fun getFavoriteMovies(): Flow<Result<List<MovieUiModel>>>
}
