package com.example.newsexpress.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsexpress.presentation.utils.BottomNavigation
import com.example.newsexpress.viewmodel.NewsViewModel

@Composable
fun HomePage(viewModel: NewsViewModel = hiltViewModel()){
    HomePageItem(viewModel)
}

@Composable
fun HomePageItem(viewModel: NewsViewModel){
    BottomNavigation(viewModel)
}
