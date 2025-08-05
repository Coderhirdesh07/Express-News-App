package com.example.newsexpress.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsexpress.newsapidata.Article
import com.example.newsexpress.presentation.components.NewsItem
import com.example.newsexpress.viewmodel.NewsViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(FlowPreview::class)
@Composable
fun SearchScreen(newsViewModel: NewsViewModel) {
    Text("Search Screen")
    val newsData by newsViewModel.newsData.collectAsState()
    val error by newsViewModel.error.collectAsState()
    var query by remember { mutableStateOf("") }

    LaunchedEffect(query) {
        snapshotFlow { query }
            .debounce(500)
            .filter { it.isNotBlank() }
            .distinctUntilChanged()
            .collectLatest { newsViewModel.getNewsArticleFromNetwork(it, language = "en") }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search News") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            singleLine = true,
        )
        if (error != null) {
            Text(text = error ?: "Error Message", color = Color.Red, modifier = Modifier.padding(8.dp))
        }
        val articles: List<Article> = newsData?.article.orEmpty()
        NewsItem(itemList = articles)
    }
}
