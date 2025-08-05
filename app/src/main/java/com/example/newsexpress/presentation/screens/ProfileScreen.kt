package com.example.newsexpress.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsexpress.presentation.components.DropDownMenuSelector
import com.example.newsexpress.presentation.components.LanguageList
import com.example.newsexpress.presentation.components.RegionalList
import com.example.newsexpress.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(newsViewModel: NewsViewModel) {
    val regionList = remember { RegionalList() }
    val languageList = remember { LanguageList() }

    val coroutineScope = rememberCoroutineScope()

    var selectedRegion by remember { mutableStateOf<String>("us") }
    var language by remember { mutableStateOf<String>("en") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DropDownMenuSelector(
            regionList,
            selectedRegion,
            onItemSelected = {
                selectedRegion = it
                coroutineScope.launch {
                    newsViewModel.saveDataStoreInfoRegion(it)
                }
            },
        )
        DropDownMenuSelector(
            languageList,
            language,
            onItemSelected = {
                language = it
                coroutineScope.launch {
                    newsViewModel.saveDataStoreInfoLanguage(it)
                }
            },
        )
    }
    LaunchedEffect(Unit) {
        selectedRegion = newsViewModel.region
        language = newsViewModel.language
    }
}
