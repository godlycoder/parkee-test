package com.banidevv.parkeetest.domain.repository

import com.banidevv.parkeetest.domain.uimodel.ReviewUiModel
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    suspend fun getMovieReview(movieId: Int): Flow<Result<List<ReviewUiModel>>>
}
