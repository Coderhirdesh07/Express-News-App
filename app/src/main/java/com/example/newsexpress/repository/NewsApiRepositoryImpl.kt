package com.example.newsexpress.repository

import com.example.newsexpress.ArticleDTO
import com.example.newsexpress.NewsApiRepository
import com.example.newsexpress.NewsApiService
import com.example.newsexpress.NewsDao
import com.example.newsexpress.Resource
import com.example.newsexpress.datastore.PreferenceUtils
import com.example.newsexpress.newsapidata.NewsApiData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsApiRepositoryImpl
    @Inject
    constructor(
        private val newsDataStore: PreferenceUtils,
        private val newsApiService: NewsApiService,
        private val newsDao: NewsDao,
    ) : NewsApiRepository {
        // datastore functions

        override suspend fun saveLanguage(language: String) {
            newsDataStore.saveString("language_key", language)
        }

        override suspend fun getLanguage(): String? = newsDataStore.getString("language_key")

        override suspend fun saveRegion(region: String) {
            newsDataStore.saveString("region_key", region)
        }

        override suspend fun getRegion(): String? = newsDataStore.getString("region_key")

        override fun getNewsArticle(
            q: String,
            language: String,
        ): Flow<Resource<NewsApiData>> =
            flow {
                try {
                    emit(Resource.Loading(true))
                    val data = newsApiService.getNewsArticle(q, language).data
                    if (data != null) {
                        emit(Resource.Success(data))
                    } else {
                        emit(Resource.Error(message = "Error found", data = null))
                    }
                } catch (e: Exception) {
                    emit(value = Resource.Error(message = e.message.toString(), data = null))
                }
            }

        override fun getNewsArticleCategory(
            category: String,
            language: String,
        ): Flow<Resource<NewsApiData>> =
            flow {
                try {
                    emit(Resource.Loading(true))
                    val data = newsApiService.getNewsArticleWithCategory(category, language).data
                    if (data != null) {
                        emit(Resource.Success(data))
                    } else {
                        emit(Resource.Error(message = "Data Cannot be fetched", data = null))
                    }
                } catch (e: Exception) {
                    emit(Resource.Error(message = e.message.toString(), data = null))
                }
            }

        override fun getNewsTopHeadLines(country: String): Flow<Resource<NewsApiData>> =
            flow {
                try {
                    emit(Resource.Loading(true))
                    val data = newsApiService.getNewsHeadlines(country).data
                    if (data != null) {
                        emit(Resource.Success(data))
                    } else {
                        emit(Resource.Error(message = "Data Cannot Be fetched", null))
                    }
                } catch (e: Exception) {
                    emit(Resource.Error(message = "Data Cannot be fetched", null))
                }
            }

        // for room database

        override fun getSavedNewsArticle(): Flow<Resource<List<ArticleDTO>>> =
            flow {
                try {
                    emit(Resource.Loading(true))
                    val articleDTO = newsDao.getListOfArticle()
                    emit(Resource.Success(articleDTO))
                } catch (e: Exception) {
                    emit(Resource.Error("Database item Cannot Be Fetched", null))
                }
            }

        override suspend fun insertNewsArticle(articleDTO: ArticleDTO) {
            newsDao.insertArticle(articleDTO)
        }

        override suspend fun deleteNewsArticle(articleDTO: ArticleDTO) {
            newsDao.deleteArticle(articleDTO)
        }
    }
