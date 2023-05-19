package com.banidevv.parkeetest.data.di;

import android.content.Context
import com.banidevv.parkeetest.data.database.AppDatabase
import com.banidevv.parkeetest.data.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun providesMovieDao(
        database: AppDatabase
    ) : MovieDao {
        return database.movieDao()
    }
}