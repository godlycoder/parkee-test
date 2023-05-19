package com.banidevv.parkeetest.data.repository

import com.banidevv.parkeetest.data.datasources.review.ReviewRemoteDataSource
import com.banidevv.parkeetest.data.mapper.toUiModel
import com.banidevv.parkeetest.domain.repository.ReviewRepository
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import com.banidevv.parkeetest.domain.uimodel.ReviewUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val remoteDataSource: ReviewRemoteDataSource
) : ReviewRepository {

    override suspend fun getMovieReview(movieId: Int): Flow<Result<List<ReviewUiModel>>> {
        val result = remoteDataSource.getMovieReview(movieId)

        return flow<Result<List<ReviewUiModel>>> {
            result.onSuccess { list ->
                val data = list.map { it.toUiModel() }
                emit(Result.success(data))
            }.onFailure {
                emit(Result.failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }
}