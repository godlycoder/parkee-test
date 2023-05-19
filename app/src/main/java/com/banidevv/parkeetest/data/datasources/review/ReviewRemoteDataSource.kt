package com.banidevv.parkeetest.data.datasources.review

import com.banidevv.parkeetest.data.dto.ReviewDTO
import com.banidevv.parkeetest.data.services.ApiServices
import javax.inject.Inject

class ReviewRemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) {
    suspend fun getMovieReview(movieId: Int): Result<List<ReviewDTO>> {
        return try {
            val response = apiServices.getMovieReview(movieId)
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
