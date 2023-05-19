package com.banidevv.parkeetest.domain.usecase

import com.banidevv.parkeetest.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class CheckIsFavorite(
    private val repository: MovieRepository
) {

    suspend fun execute(movieId: Int) : Flow<Result<Boolean>> {
        return repository.checkIsFavorite(movieId)
    }

}
