package com.example.newsexpress.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.newsexpress.R

@Composable
fun CategorieSection(){
    val categoryList:List<CategoryItem> = getCategoryList()
    CategoryCard(categoryList)
}


@Composable
fun CategoryCard(categoryList:List<CategoryItem>){
    LazyRow {
        items(categoryList){ it->
            Card(modifier = Modifier.width(150.dp).height(height = 120.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = it.categoryColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)){
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(it.categoryImage), contentDescription = "Items", modifier = Modifier.width(45.dp).height(45.dp))
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(it.categoryName, fontSize = 20.sp)
                }
            }
        }
    }

}
data class CategoryItem(
    val categoryName:String,
    val categoryImage:Int,
    val categoryColor:Color
)

fun getCategoryList():List<CategoryItem>{
//    return listOf<String>("General","Science","Health","Sports","Business")
    return listOf<CategoryItem>(
        CategoryItem("General",R.drawable.ic_launcher_foreground,Color.Green),
        CategoryItem("Science",R.drawable.ic_launcher_foreground,Color.Cyan),
        CategoryItem("Health",R.drawable.ic_launcher_foreground,Color.Blue),
        CategoryItem("Sports",R.drawable.ic_launcher_foreground,Color.Gray),
        CategoryItem("Business",R.drawable.ic_launcher_foreground,Color.Magenta)
    )
}