package com.banidevv.parkeetest.domain.uimodel

data class MovieUiModel(
    val id: Int,
    val title: String,
    val backdropUrl: String,
    val posterUrl: String,
    val rating: Double,
    val overview: String,
)
