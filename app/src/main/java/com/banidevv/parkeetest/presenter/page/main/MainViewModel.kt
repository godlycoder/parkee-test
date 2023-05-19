package com.banidevv.parkeetest.presenter.page.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import com.banidevv.parkeetest.domain.usecase.GetNowPlayingMovies
import com.banidevv.parkeetest.domain.usecase.GetPopularMovies
import com.banidevv.parkeetest.domain.usecase.GetTopRatedMovies
import com.banidevv.parkeetest.presenter.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMovies : GetPopularMovies,
    private val getTopRatedMovies : GetTopRatedMovies,
    private val getNowPlayingMovies : GetNowPlayingMovies,
) : ViewModel() {

    private val _popularUiState = MutableLiveData<UiState<List<MovieUiModel>>>()
    val popularUiState by lazy<LiveData<UiState<List<MovieUiModel>>>> {
        _popularUiState
    }

    private val _topRatedUiState = MutableLiveData<UiState<List<MovieUiModel>>>()
    val topRatedUiState by lazy<LiveData<UiState<List<MovieUiModel>>>> {
        _topRatedUiState
    }

    private val _nowPlayingUiState = MutableLiveData<UiState<List<MovieUiModel>>>()
    val nowPlayingUiState by lazy<LiveData<UiState<List<MovieUiModel>>>> {
        _nowPlayingUiState
    }

    fun getPopularMovies() = viewModelScope.launch {
        _popularUiState.value = UiState.Loading(true)

        getPopularMovies.execute().collect { result ->
            result.onSuccess { data ->
                _popularUiState.value = UiState.Loading(true)
                _popularUiState.value = UiState.Success(data)
            }.onFailure {
                _popularUiState.value = UiState.Loading(true)
                _popularUiState.value = UiState.Error(it.message.toString())
            }
        }
    }

    fun getTopRatedMovies() = viewModelScope.launch {
        _topRatedUiState.value = UiState.Loading(true)

        getTopRatedMovies.execute().collect { result ->
            result.onSuccess { data ->
                _topRatedUiState.value = UiState.Loading(true)
                _topRatedUiState.value = UiState.Success(data)
            }.onFailure {
                _topRatedUiState.value = UiState.Loading(true)
                _topRatedUiState.value = UiState.Error(it.message.toString())
            }
        }
    }

    fun getNowPlayingMovies() = viewModelScope.launch {
        _nowPlayingUiState.value = UiState.Loading(true)

        getNowPlayingMovies.execute().collect { result ->
            result.onSuccess { data ->
                _nowPlayingUiState.value = UiState.Loading(true)
                _nowPlayingUiState.value = UiState.Success(data)
            }.onFailure {
                _nowPlayingUiState.value = UiState.Loading(true)
                _nowPlayingUiState.value = UiState.Error(it.message.toString())
            }
        }
    }
}