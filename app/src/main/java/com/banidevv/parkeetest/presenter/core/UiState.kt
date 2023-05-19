package com.banidevv.parkeetest.presenter.core

sealed class UiState<out T> {
    data class Loading(val isLoading: Boolean) : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
