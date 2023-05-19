package com.banidevv.parkeetest.data.services

import com.banidevv.parkeetest.BuildConfig
import com.banidevv.parkeetest.data.dto.MovieDTO
import com.banidevv.parkeetest.data.dto.ReviewDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("movie/popular?language=en-US&page=1&api_key=${BuildConfig.MOVIE_API_KEY}")
    suspend fun getPopularMovies(): Response<MovieDTO>

    @GET("movie/top_rated?language=en-US&page=1&api_key=${BuildConfig.MOVIE_API_KEY}")
    suspend fun getTopRatedMovies(): Response<MovieDTO>

    @GET("movie/now_playing?language=en-US&page=1&api_key=${BuildConfig.MOVIE_API_KEY}")
    suspend fun getNowPlayingMovies(): Response<MovieDTO>

    @GET("movie/{movieId}/reviews?api_key=${BuildConfig.MOVIE_API_KEY}")
    suspend fun getMovieReview(@Path("movieId") movieId: Int): Response<ReviewDTO>

}
