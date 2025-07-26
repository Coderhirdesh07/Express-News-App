package com.example.newsexpress.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsexpress.newsapidata.Article


@Composable
fun NewsItem(itemList:List<Article>){
    LazyColumn {
        items(itemList){
            Box(modifier= Modifier.fillMaxWidth().fillMaxHeight(0.25f)){
                Row{
                    AsyncImage(model=it.urtToImage, contentDescription = "Image from url", modifier = Modifier.fillMaxWidth())
                      Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.Start){
                          Text(text = it.title, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                          Spacer(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f))
                          Text(text = it.description, fontSize = 14.sp)
                      }

                }
            }
        }
    }
}