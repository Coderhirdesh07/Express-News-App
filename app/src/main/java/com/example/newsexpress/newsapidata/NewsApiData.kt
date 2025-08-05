package com.example.newsexpress.newsapidata

data class NewsApiData(
    val article: List<Article>,
    val status: String,
    val totalResults: Int,
)
