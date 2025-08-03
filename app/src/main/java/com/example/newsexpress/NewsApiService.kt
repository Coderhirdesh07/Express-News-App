package com.example.newsexpress


import com.example.newsexpress.newsapidata.NewsApiData
import kotlinx.coroutines.flow.Flow

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

    @Headers("api-key:" + "6fa37c2d9a784fb58ff0cf1a80f044e3")
    @GET("/everything")
    suspend fun  getNewsArticle(@Query("q") q:String,@Query("language") language:String): Resource<NewsApiData>

    @Headers("api-key:" + "6fa37c2d9a784fb58ff0cf1a80f044e3")
    @GET("/everything")
    suspend fun  getNewsArticleWithCategory(@Query("category") category:String,@Query("language") language: String):Resource<NewsApiData>

    @Headers("api-key:" + "6fa37c2d9a784fb58ff0cf1a80f044e3")
    @GET("/top-headlines")
    suspend fun getNewsHeadlines(@Query("country") country:String): Resource<NewsApiData>
}