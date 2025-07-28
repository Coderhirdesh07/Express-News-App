package com.example.newsexpress.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.newsexpress.presentation.components.Categories
import com.example.newsexpress.presentation.components.NewsItem
import com.example.newsexpress.viewmodel.NewsViewModel

@Composable
fun HomeScreen(newsViewModel: NewsViewModel){
    val newsListState by newsViewModel.newsData.collectAsState()
    val newsListData = newsListState?.article?:emptyList()

    Categories(newsViewModel)
    Text("News", fontSize = 15.sp, fontWeight = FontWeight.Bold)
    NewsItem(newsListData)
}