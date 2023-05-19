package com.banidevv.parkeetest.domain.usecase

import com.banidevv.parkeetest.domain.repository.MovieRepository
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import kotlinx.coroutines.flow.Flow

class GetTopRatedMovies(
    private val repository: MovieRepository
) {
    suspend fun execute(): Flow<Result<List<MovieUiModel>>> {
        return repository.getTopRatedMovies()
    }

}
