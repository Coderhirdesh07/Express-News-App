package com.example.newsexpress

import com.example.newsexpress.newsapidata.NewsApiData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NewsApiRepository {
    fun getNewsArticle(q:String): Flow<Resource<NewsApiData>>

    fun getNewsTopHeadLines(): Flow<Resource<NewsApiData>>


    // for database
    fun getSavedNewsArticle():Flow<Resource<List<ArticleDTO>>>

   suspend fun insertNewsArticle(articleDTO: ArticleDTO)

    suspend fun deleteNewsArticle(articleDTO: ArticleDTO)
}
