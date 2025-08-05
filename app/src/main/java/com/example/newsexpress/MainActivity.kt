package com.example.newsexpress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsexpress.presentation.HomePage
import com.example.newsexpress.ui.theme.NewsExpressTheme
import com.example.newsexpress.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsExpressTheme {
                val viewmodel: NewsViewModel = hiltViewModel()
                HomePage(viewmodel)
            }
        }
    }
}
