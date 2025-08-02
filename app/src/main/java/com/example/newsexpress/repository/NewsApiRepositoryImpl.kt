package com.example.newsexpress.repository

import androidx.lifecycle.asLiveData
import com.example.newsexpress.ArticleDTO
import com.example.newsexpress.NewsApiRepository
import com.example.newsexpress.NewsApiService
import com.example.newsexpress.NewsDao
import com.example.newsexpress.Resource
import com.example.newsexpress.newsapidata.NewsApiData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsApiRepositoryImpl(private val newsApiService: NewsApiService,private val newsDao: NewsDao): NewsApiRepository{

    override fun getNewsArticle(q: String): Flow<Resource<NewsApiData>> = flow {
        try {
            emit(Resource.Loading(true))
            val data = newsApiService.getNewsArticle(q).data
            if (data != null) {
                emit(Resource.Success(data))
            } else {
                emit(Resource.Error(message = "Error found", data = null))
            }
        }
        catch (e: Exception){
            emit(value = Resource.Error(message = e.message.toString(),data=null))
        }
    }
    override fun getNewsArticleCategory(category: String): Flow<Resource<NewsApiData>> = flow {
        try{
            emit(Resource.Loading(true))
            val data = newsApiService.getNewsArticleWithCategory(category).data
            if(data!=null){
                emit(Resource.Success(data))
            }
            else{
                emit(Resource.Error(message="Data Cannot be fetched",data = null))
            }
        }
        catch (e: Exception){
            emit(Resource.Error(message = e.message.toString(), data = null))
        }
    }

    override fun getNewsTopHeadLines(): Flow<Resource<NewsApiData>> = flow {
        try {
            emit(Resource.Loading(true))
            val data = newsApiService.getNewsHeadlines().data
            if(data!=null){
                emit(Resource.Success(data))
            }
            else{
                emit(Resource.Error(message = "Data Cannot Be fetched",null))
            }
        }
        catch (e: Exception){
            emit(Resource.Error(message = "Data Cannot be fetched",null))
        }
    }



    // for room database

    override  fun getSavedNewsArticle(): Flow<Resource<List<ArticleDTO>>> = flow{
        try {
            emit(Resource.Loading(true))
            val articleDTO = newsDao.getListOfArticle()
            emit(Resource.Success(articleDTO))
        }
        catch (e: Exception){
            emit(Resource.Error("Database item Cannot Be Fetched",null))
        }

    }

    override suspend fun insertNewsArticle(articleDTO: ArticleDTO) {
        newsDao.insertArticle(articleDTO)
    }

    override suspend fun deleteNewsArticle(articleDTO: ArticleDTO) {
            newsDao.deleteArticle(articleDTO)
    }

}
