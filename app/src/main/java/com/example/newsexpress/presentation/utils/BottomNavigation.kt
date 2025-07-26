package com.example.newsexpress.presentation.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(){
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selectedScreen = remember(){
        mutableStateOf(Icons.Default.Home)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = Color.Green){
                IconButton(onClick = {
                    selectedScreen.value = Icons.Default.Home
                    navigationController.navigate(Screens.HomeScreen){ popUpTo(0) }
                }, modifier = Modifier.weight(1f)){
                    Icon(Icons.Default.Home, contentDescription = "Home Icons", modifier = Modifier.size(26.dp))
                }

                IconButton(onClick = {
                    selectedScreen.value = Icons.Default.Search
                    navigationController.navigate(Screens.SearchScreen){ popUpTo(0) }
                }, modifier = Modifier.weight(1f)){
                    Icon(Icons.Default.Search, contentDescription = "Search Icons", modifier = Modifier.size(26.dp))
                }

                IconButton(onClick = {
                    selectedScreen.value = Icons.Default.Favorite
                    navigationController.navigate(Screens.SavedArticleScreen){ popUpTo(0) }
                }, modifier = Modifier.weight(1f)){
                    Icon(Icons.Default.Favorite, contentDescription = "Saved Icons", modifier = Modifier.size(26.dp))
                }
            }
        }
    )
    { paddingValues ->

        NavHost(navController = navigationController, startDestination = Screens.HomeScreen, modifier = Modifier.padding(paddingValues)){
            composable(Screens.HomeScreen.screen){}
            composable(Screens.SearchScreen.screen){}
            composable(Screens.SavedArticleScreen.screen){}
        }
    }

}
sealed class Screens(val screen:String){
    data object HomeScreen: Screens("home")
    data object SavedArticleScreen: Screens("saved")
    data object SearchScreen:Screens("search")
}