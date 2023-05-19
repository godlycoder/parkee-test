package com.banidevv.parkeetest.data.dto

data class ReviewDTO(
    val author: String,
    val author_details: AuthorDetailsDTO,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)