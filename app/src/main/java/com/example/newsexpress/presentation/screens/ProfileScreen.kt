package com.example.newsexpress.presentation.screens

import android.content.Context
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
import com.example.newsexpress.datastore.PreferenceUtils
import com.example.newsexpress.presentation.components.DropDownMenuSelector
import com.example.newsexpress.presentation.components.LanguageList
import com.example.newsexpress.presentation.components.RegionalList
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(context: Context){
    val regionList = remember {  RegionalList() }
    val languageList = remember { LanguageList() }
    val preferenceUtils = remember { PreferenceUtils(context) }
    val coroutineScope = rememberCoroutineScope()

    var selectedRegion by remember { mutableStateOf<String>("us") }
    var language by remember { mutableStateOf<String>("en") }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)){
            DropDownMenuSelector(
                regionList,
                selectedRegion,
                onItemSelected = {selectedRegion = it
                    coroutineScope.launch {
                        preferenceUtils.saveString("selected_region",it)
                    }
                }
            )
            DropDownMenuSelector(
                languageList,
                language,
                onItemSelected = {
                    language = it
                    coroutineScope.launch {
                        preferenceUtils.saveString("selected_language",it)
                    }
                })
        }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            selectedRegion = preferenceUtils.getString("selected_region") ?: "us"
            language = preferenceUtils.getString("selected_language") ?: "en"
        }
    }
}
