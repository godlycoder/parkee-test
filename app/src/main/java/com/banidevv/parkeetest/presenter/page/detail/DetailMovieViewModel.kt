package com.banidevv.parkeetest.presenter.page.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import com.banidevv.parkeetest.domain.uimodel.ReviewUiModel
import com.banidevv.parkeetest.domain.usecase.AddToFavorite
import com.banidevv.parkeetest.domain.usecase.CheckIsFavorite
import com.banidevv.parkeetest.domain.usecase.GetMovieReviews
import com.banidevv.parkeetest.domain.usecase.RemoveFromFavorite
import com.banidevv.parkeetest.presenter.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val addToFavorite: AddToFavorite,
    private val removeFromFavorite: RemoveFromFavorite,
    private val checkIsFavorite: CheckIsFavorite,
    private val getMovieReviews: GetMovieReviews
): ViewModel() {

    private val _favoriteUiState = MutableLiveData<UiState<Boolean>>()
    val favoriteUiState : LiveData<UiState<Boolean>> by lazy {
        _favoriteUiState
    }

    private val _reviewsUiState = MutableLiveData<UiState<List<ReviewUiModel>>>()
    val reviewsUiState : LiveData<UiState<List<ReviewUiModel>>> by lazy {
        _reviewsUiState
    }

    fun getMovieReviews(movieId: Int) = viewModelScope.launch {
        _reviewsUiState.value = UiState.Loading(true)

        getMovieReviews.execute(movieId).collect { result ->
            result.onSuccess {
                _reviewsUiState.value = UiState.Loading(false)
                _reviewsUiState.value = UiState.Success(it)
            }.onFailure {
                _reviewsUiState.value = UiState.Loading(false)
                _reviewsUiState.value = UiState.Error(it.toString())
            }
        }
    }

    fun checkIsFavorite(movieId: Int) = viewModelScope.launch {
        _favoriteUiState.value = UiState.Loading(true)

        checkIsFavorite.execute(movieId).collect { result ->
            result.onSuccess {
                _favoriteUiState.value = UiState.Loading(false)
                _favoriteUiState.value = UiState.Success(it)
            }.onFailure {
                _favoriteUiState.value = UiState.Loading(false)
                _favoriteUiState.value = UiState.Error(it.message.toString())
            }
        }
    }

    fun addToFavorite(movieUiModel : MovieUiModel) = viewModelScope.launch {
        _favoriteUiState.value = UiState.Loading(true)

        addToFavorite.execute(movieUiModel).collect { result ->
            result.onSuccess {
                _favoriteUiState.value = UiState.Loading(false)
                _favoriteUiState.value = UiState.Success(true)
            }.onFailure {
                _favoriteUiState.value = UiState.Loading(false)
                _favoriteUiState.value = UiState.Error(it.message.toString())
            }
        }
    }

    fun removeFromFavorite(movieUiModel : MovieUiModel) = viewModelScope.launch {
        _favoriteUiState.value = UiState.Loading(true)

        removeFromFavorite.execute(movieUiModel).collect { result ->
            result.onSuccess {
                _favoriteUiState.value = UiState.Loading(false)
                _favoriteUiState.value = UiState.Success(false)
            }.onFailure {
                _favoriteUiState.value = UiState.Loading(false)
                _favoriteUiState.value = UiState.Error(it.message.toString())
            }
        }
    }

}