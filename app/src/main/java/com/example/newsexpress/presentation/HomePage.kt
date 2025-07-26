package com.example.newsexpress.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.newsexpress.newsapidata.Article

import com.example.newsexpress.presentation.components.CategorieSection
import com.example.newsexpress.presentation.components.NewsItem
import com.example.newsexpress.presentation.utils.BottomNavigationBar
import com.example.newsexpress.viewmodel.NewsViewModel

@Composable
fun HomePage(viewModel: NewsViewModel = hiltViewModel()){
     val newsList:List<Article> = viewModel.newsData.value?.article?:emptyList()
      CategorieSection()
      NewsItem(newsList)
}

@Composable
fun HomePageItem(){
    BottomNavigationBar()
}
