package com.banidevv.parkeetest.data.mapper

import com.banidevv.parkeetest.data.database.entity.MovieEntity
import com.banidevv.parkeetest.data.dto.MovieDTO
import com.banidevv.parkeetest.data.dto.ReviewDTO
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import com.banidevv.parkeetest.domain.uimodel.ReviewUiModel

fun MovieDTO.toUiModel() : MovieUiModel {
    return MovieUiModel(
        id,
        title,
        "https://image.tmdb.org/t/p/original/$backdrop_path",
        "https://image.tmdb.org/t/p/original/$poster_path",
        vote_average,
        overview
    )
}

fun MovieEntity.toUiModel() : MovieUiModel {
    return MovieUiModel(
        id,
        title,
        backdropUrl,
        posterUrl,
        rating,
        overview
    )
}

fun MovieUiModel.toEntity() : MovieEntity {
    return MovieEntity(
        id,
        title,
        overview,
        backdropUrl,
        posterUrl,
        rating
    )
}

fun ReviewDTO.toUiModel() : ReviewUiModel {
    return ReviewUiModel(
        author,
        author_details.avatar_path,
        content
    )
}

