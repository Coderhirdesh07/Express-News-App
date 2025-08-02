package com.example.newsexpress.repository

import com.example.newsexpress.ArticleDTO
import com.example.newsexpress.NewsApiService
import com.example.newsexpress.NewsDao
import com.example.newsexpress.Resource
import com.example.newsexpress.newsapidata.Article
import com.example.newsexpress.newsapidata.NewsApiData
import com.example.newsexpress.newsapidata.Source
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestFactory
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response
import kotlin.String

class NewsApiRepositoryImplTest {

    private val newsApiService: NewsApiService = mockk<NewsApiService>()
    private val newsTestDao: NewsDao = mockk<NewsDao>(relaxed = true)
    private lateinit var repositoryImpl: NewsApiRepositoryImpl
    @Before
    fun setup(){
        repositoryImpl = NewsApiRepositoryImpl(newsApiService,newsTestDao)
    }


    @Test
   fun getNewsArticle_success_Response() = runTest{
       val fakeData = getNewsArticleFakeData()
        val fakeResponse = Resource.Success(fakeData)
        coEvery { newsApiService.getNewsArticle("Bitcoin") } returns fakeResponse
        val result =  repositoryImpl.getNewsArticle("Bitcoin").toList()

        val loading = result[0]
        val success = result[1]
        assertTrue(loading is Resource.Loading)
        assertTrue(success is Resource.Success)
        assertEquals(fakeData,(success as Resource.Success).data)
   }

    @Test
    fun getNewsArticle_failed_Response() = runTest {
        val errorMsg = "Network failure"
        coEvery { newsApiService.getNewsArticle("Bitcoin") } throws RuntimeException(errorMsg)
        val result = repositoryImpl.getNewsArticle("Bitcoin").toList()

        val loading = result[0]
        val error = result[1]

        assertTrue (loading is Resource.Loading )
        assertTrue(error is Resource.Error)
        assertEquals(errorMsg,(error as Resource.Error).message)
    }

    @Test
    fun getNewsTopHeadLines_successful_Response() = runTest{
        val fakeData = getNewsArticleFakeData()
        val fakeResponse = Resource.Success(fakeData)
       coEvery { newsApiService.getNewsHeadlines() } returns fakeResponse
        val result =  repositoryImpl.getNewsTopHeadLines().toList()

        val loading = result[0]
        val data = result[1]
        assertEquals(fakeData,(data as Resource.Success).data)
    }
    @Test
    fun getNewsTopHeadlines_failure_Response() = runTest {
        val fakeData = getNewsArticleFakeData()
        val fakeResponse = Resource.Success(fakeData)
        coEvery { newsApiService.getNewsHeadlines() } returns fakeResponse
        val result = repositoryImpl.getNewsTopHeadLines().toList()
        val loading = result[0]
        val data = result[1]

    }


//
//    @Test
//    fun getSavedNewsArticle_successfulResponse(){
//
//    }
//
    fun getNewsArticleFakeData(): NewsApiData{
        val newsList  = getNewsArticleFakeList()
        return NewsApiData(newsList,"Success",1)
    }
    fun getNewsArticleFakeList():List<Article>{
        val fakeData = Article(
            author="BBC",
            content="Bitcoin is rising rapidly",
            description = "Bitcoin is good",
            publishedAt = "2024",
            source = Source("1", "Title"),
            title="Title",
            url="https://fakeid",
            urlToImage = "https://fakeid"
        )
        return listOf(fakeData)
    }


}
