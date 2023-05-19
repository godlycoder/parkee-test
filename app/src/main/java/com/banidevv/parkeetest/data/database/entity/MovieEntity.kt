package com.banidevv.parkeetest.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie"
)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val backdropUrl: String,
    val posterUrl: String,
    val rating: Double
)
