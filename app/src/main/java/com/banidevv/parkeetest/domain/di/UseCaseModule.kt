package com.banidevv.parkeetest.domain.di

import com.banidevv.parkeetest.domain.repository.MovieRepository
import com.banidevv.parkeetest.domain.repository.ReviewRepository
import com.banidevv.parkeetest.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providesGetPopularMovies(
        repository: MovieRepository
    ) : GetPopularMovies {
        return GetPopularMovies(repository)
    }

    @Provides
    fun providesGetTopRatedMovies(
        repository: MovieRepository
    ) : GetTopRatedMovies {
        return GetTopRatedMovies(repository)
    }

    @Provides
    fun providesGetNowPlayingMovies(
        repository: MovieRepository
    ) : GetNowPlayingMovies {
        return GetNowPlayingMovies(repository)
    }

    @Provides
    fun providesAddToFavorite(
        repository: MovieRepository
    ) : AddToFavorite {
        return AddToFavorite(repository)
    }

    @Provides
    fun providesRemoveFromFavorite(
        repository: MovieRepository
    ) : RemoveFromFavorite {
        return RemoveFromFavorite(repository)
    }

    @Provides
    fun providesCheckIsFavorite(
        repository: MovieRepository
    ) : CheckIsFavorite {
        return CheckIsFavorite(repository)
    }


    @Provides
    fun providesGetMovieReviews(
        repository: ReviewRepository
    ) : GetMovieReviews {
        return GetMovieReviews(repository)
    }

    @Provides
    fun providesGetFavoriteMovies(
        repository: MovieRepository
    ) : GetFavoriteMovies {
        return GetFavoriteMovies(repository)
    }
}