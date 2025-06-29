package com.example.newsexpress.di

import com.example.newsexpress.NewsApiRepository
import com.example.newsexpress.NewsApiService
import com.example.newsexpress.NewsDao
import com.example.newsexpress.repository.NewsApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsRepository(newsApiService: NewsApiService,newsDao: NewsDao): NewsApiRepository{
        return NewsApiRepositoryImpl(newsApiService,newsDao)

    }


}