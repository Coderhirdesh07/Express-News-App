package com.example.newsexpress.di

import com.example.newsexpress.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofitInstance(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https:google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesNewsApiService(retrofit: Retrofit): NewsApiService = retrofit.create(NewsApiService::class.java)
}
