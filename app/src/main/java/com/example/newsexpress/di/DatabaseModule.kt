package com.example.newsexpress.di

import android.content.Context
import androidx.room.Room
import com.example.newsexpress.NewsDao
import com.example.newsexpress.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesNewsDatabase(
        @ApplicationContext context: Context,
    ): NewsDataBase {
        val db = Room.databaseBuilder(context, NewsDataBase::class.java, "newsdb").build()
        return db
    }

    @Provides
    @Singleton
    fun providesNewsDao(newsDataBase: NewsDataBase): NewsDao = newsDataBase.getNewsDao()
}
