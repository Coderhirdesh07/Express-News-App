package com.example.newsexpress.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsexpress.newsapidata.Article


@Composable
fun NewsItem(itemList:List<Article>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(itemList){
            Box(modifier= Modifier.fillMaxWidth().padding(8.dp)){
                Row(modifier = Modifier.fillMaxWidth()){
                    AsyncImage(model=it.urlToImage, contentDescription = "Image from url",
                        modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
                      Spacer(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f))
                      Column(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)
                          ,verticalArrangement = Arrangement.spacedBy(4.dp)
                         )
                       {
                          Text(text = it.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis)
                          Text(text = it.description,
                              fontSize = 14.sp,
                              maxLines = 3,
                              overflow = TextOverflow.Ellipsis
                          )
                       }

                }
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), DividerDefaults.Thickness, DividerDefaults.color)
        }
    }
}