package com.banidevv.parkeetest.domain.usecase

import com.banidevv.parkeetest.domain.repository.MovieRepository
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import kotlinx.coroutines.flow.Flow

class RemoveFromFavorite(
    private val repository: MovieRepository
) {
    suspend fun execute(movieUiModel: MovieUiModel) : Flow<Result<Boolean>> {
        return repository.removeFromFavorite(movieUiModel)
    }
}
