package com.banidevv.parkeetest.data.repository

import com.banidevv.parkeetest.data.datasources.movie.MovieLocalDataSource
import com.banidevv.parkeetest.data.datasources.movie.MovieRemoteDataSource
import com.banidevv.parkeetest.data.mapper.toEntity
import com.banidevv.parkeetest.data.mapper.toUiModel
import com.banidevv.parkeetest.domain.repository.MovieRepository
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource : MovieRemoteDataSource,
    private val localDataSource : MovieLocalDataSource
) : MovieRepository {

    override suspend fun getPopularMovies(): Flow<Result<List<MovieUiModel>>> {
        val result = remoteDataSource.getPopularMovies()

        return flow<Result<List<MovieUiModel>>> {
            result.onSuccess { list ->
                val data = list.map { it.toUiModel() }
                emit(Result.success(data))
            }.onFailure {
                emit(Result.failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTopRatedMovies(): Flow<Result<List<MovieUiModel>>> {
        val result = remoteDataSource.getTopRatedMovies()

        return flow<Result<List<MovieUiModel>>> {
            result.onSuccess { list ->
                val data = list.map { it.toUiModel() }
                emit(Result.success(data))
            }.onFailure {
                emit(Result.failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getNowPlayingMovies(): Flow<Result<List<MovieUiModel>>> {
        val result = remoteDataSource.getNowPlayingMovies()

        return flow<Result<List<MovieUiModel>>> {
            result.onSuccess { list ->
                val data = list.map { it.toUiModel() }
                emit(Result.success(data))
            }.onFailure {
                emit(Result.failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addToFavorite(movieUiModel: MovieUiModel): Flow<Result<Boolean>> {
        val entity = movieUiModel.toEntity()

        return flow {
            try {
                localDataSource.addToFavorite(entity)
                emit(Result.success(true))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun removeFromFavorite(movieUiModel: MovieUiModel): Flow<Result<Boolean>> {
        val entity = movieUiModel.toEntity()

        return flow {
            try {
                localDataSource.removeFromFavorite(entity)
                emit(Result.success(false))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun checkIsFavorite(movieId: Int): Flow<Result<Boolean>> {
        return flow {
            try {
                val result = localDataSource.checkIsFavorite(movieId)
                emit(Result.success(result))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFavoriteMovies(): Flow<Result<List<MovieUiModel>>> {


        return flow {
            try {
                val result = localDataSource.getFavoriteMovies()
                val data = result.map { it.toUiModel() }

                emit(Result.success(data))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}