package com.example.newsexpress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsexpress.ArticleDTO
import com.example.newsexpress.NewsApiRepository
import com.example.newsexpress.newsapidata.NewsApiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
    @Inject
    constructor(
        val newsApiRepository: NewsApiRepository,
    ) : ViewModel() {
        // state handles code

        var region: String = "us"
        var language: String = "en"

        private var _newsData = MutableStateFlow<NewsApiData?>(null)
        var newsData: StateFlow<NewsApiData?> = _newsData.asStateFlow()

        private val _error = MutableStateFlow<String?>(null)
        val error: StateFlow<String?> = _error.asStateFlow()

        init {
            viewModelScope.launch {
                getDataStoreInfo()
                getNewsHeadlines(region)
            }
        }

        suspend fun saveDataStoreInfoRegion(value: String) {
            newsApiRepository.saveRegion(value)
        }

        suspend fun saveDataStoreInfoLanguage(value: String) {
            newsApiRepository.saveLanguage(value)
        }

        suspend fun getDataStoreInfo() {
            region = newsApiRepository.getRegion() ?: region
            language = newsApiRepository.getLanguage() ?: language
        }

        fun getNewsArticleFromNetwork(
            query: String,
            language: String,
        ) {
            viewModelScope.launch {
                newsApiRepository
                    .getNewsArticle(query, language)
                    .catch { e ->
                        _error.value = e.message ?: "Unknown Error"
                    }.collect { news ->
                        _newsData.value = news.data
                    }
            }
        }

        fun getNewsHeadlines(region: String) {
            viewModelScope.launch {
                newsApiRepository
                    .getNewsTopHeadLines(region)
                    .catch { e ->
                        _error.value = e.message ?: "Unknown Error"
                    }.collect { news ->
                        _newsData.value = news.data
                    }
            }
        }

        fun getNewsArticleWithCategory(
            category: String,
            language: String,
        ) {
            viewModelScope.launch {
                newsApiRepository
                    .getNewsArticleCategory(category, language)
                    .catch { e ->
                        _error.value = e.message ?: "Data Not Fetched"
                    }.collect { news ->
                        _newsData.value = news.data
                    }
            }
        }

        // for database

        private var _localNewsList = MutableStateFlow<List<ArticleDTO?>?>(emptyList())
        var localNewsList: StateFlow<List<ArticleDTO?>?> = _localNewsList.asStateFlow()

        private val _databaseError = MutableStateFlow<String?>(null)
        val databaseError: StateFlow<String?> = _databaseError.asStateFlow()

        fun getSavedNewsArticle() {
            viewModelScope.launch {
                newsApiRepository
                    .getSavedNewsArticle()
                    .catch { e ->
                        _databaseError.value = e.message ?: "Database Error"
                    }.collect { news ->
                        _localNewsList.value = news.data
                    }
            }
        }

        fun insertNewsArticle(articleDTO: ArticleDTO) {
            viewModelScope.launch {
                newsApiRepository.deleteNewsArticle(articleDTO)
            }
        }

        fun deleteNewsArticle(articleDTO: ArticleDTO) {
            viewModelScope.launch {
                newsApiRepository.deleteNewsArticle(articleDTO)
            }
        }
    }
