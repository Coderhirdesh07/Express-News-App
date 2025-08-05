package com.example.newsexpress

import com.example.newsexpress.newsapidata.NewsApiData
import kotlinx.coroutines.flow.Flow

interface NewsApiRepository {
    suspend fun saveLanguage(language: String)

    suspend fun getLanguage(): String?

    suspend fun saveRegion(region: String)

    suspend fun getRegion(): String?

    fun getNewsArticle(
        q: String,
        language: String,
    ): Flow<Resource<NewsApiData>>

    fun getNewsTopHeadLines(country: String): Flow<Resource<NewsApiData>>

    fun getNewsArticleCategory(
        category: String,
        language: String,
    ): Flow<Resource<NewsApiData>>

    // for database
    fun getSavedNewsArticle(): Flow<Resource<List<ArticleDTO>>>

    suspend fun insertNewsArticle(articleDTO: ArticleDTO)

    suspend fun deleteNewsArticle(articleDTO: ArticleDTO)
}
