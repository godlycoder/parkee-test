package com.banidevv.parkeetest.domain.usecase

import com.banidevv.parkeetest.domain.repository.ReviewRepository
import com.banidevv.parkeetest.domain.uimodel.ReviewUiModel
import kotlinx.coroutines.flow.Flow

class GetMovieReviews(
    private val repository: ReviewRepository
) {

    suspend fun execute(movieId: Int) : Flow<Result<List<ReviewUiModel>>> {
        return repository.getMovieReview(movieId)
    }

}
