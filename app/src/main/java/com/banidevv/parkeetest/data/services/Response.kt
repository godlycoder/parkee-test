package com.banidevv.parkeetest.data.services

data class Response<T>(
    val page: Int,
    val results: List<T>
)