package com.banidevv.parkeetest.presenter.page.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import com.banidevv.parkeetest.domain.usecase.GetFavoriteMovies
import com.banidevv.parkeetest.presenter.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteMovies : GetFavoriteMovies
) : ViewModel() {

    private val _moviesUiState = MutableLiveData<UiState<List<MovieUiModel>>>()
    val moviesUiState : LiveData<UiState<List<MovieUiModel>>> by lazy {
        _moviesUiState
    }

    fun getFavoriteMovies() = viewModelScope.launch {
        _moviesUiState.value = UiState.Loading(true)

        getFavoriteMovies.execute().collect { result ->
            result.onSuccess {
                _moviesUiState.value = UiState.Loading(true)
                _moviesUiState.value = UiState.Success(it)
            }.onFailure {
                _moviesUiState.value = UiState.Loading(true)
                _moviesUiState.value = UiState.Error(it.message.toString())
            }
        }
    }

}