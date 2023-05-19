package com.banidevv.parkeetest.data.di

import com.banidevv.parkeetest.data.repository.MovieRepositoryImpl
import com.banidevv.parkeetest.data.repository.ReviewRepositoryImpl
import com.banidevv.parkeetest.domain.repository.MovieRepository
import com.banidevv.parkeetest.domain.repository.ReviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl) : MovieRepository

    @Binds
    abstract fun bindReviewRepository(repositoryImpl: ReviewRepositoryImpl) : ReviewRepository

}