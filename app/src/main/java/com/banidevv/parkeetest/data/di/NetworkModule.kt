package com.banidevv.parkeetest.data.di

import com.banidevv.parkeetest.BuildConfig
import com.banidevv.parkeetest.data.services.ApiServices
import com.banidevv.parkeetest.data.services.okHttpClient
import com.banidevv.parkeetest.data.services.retrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpClient() : OkHttpClient {
        val builder = okHttpClient(BuildConfig.DEBUG)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesApiServices(okHttpClient: OkHttpClient): ApiServices = retrofitClient(
        okHttpClient, BuildConfig.MOVIE_URL
    ).create(ApiServices::class.java)

}