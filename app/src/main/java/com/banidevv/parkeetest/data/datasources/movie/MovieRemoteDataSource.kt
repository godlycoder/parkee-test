package com.banidevv.parkeetest.data.datasources.movie

import com.banidevv.parkeetest.data.dto.MovieDTO
import com.banidevv.parkeetest.data.services.ApiServices
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiServices : ApiServices
) {
    suspend fun getPopularMovies(): Result<List<MovieDTO>> {
        return try {
            val response = apiServices.getPopularMovies()
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTopRatedMovies(): Result<List<MovieDTO>> {
        return try {
            val response = apiServices.getTopRatedMovies()
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getNowPlayingMovies(): Result<List<MovieDTO>> {
        return try {
            val response = apiServices.getNowPlayingMovies()
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
