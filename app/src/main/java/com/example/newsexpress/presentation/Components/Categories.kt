package com.example.newsexpress.presentation.Components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategorieSection(){
    val categoryList:List<String> = getCategoryList()
    CategoryCard(categoryList)
}

@Composable
fun CategoryCard(categoryList:List<String>){
}


fun getCategoryList():List<String>{
    return listOf<String>("General","Science","Health","Sports","Business")
}