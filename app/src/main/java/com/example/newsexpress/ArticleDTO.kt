package com.example.newsexpress

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsexpress.newsapidata.Source

@Entity(tableName = "newsdb")
data class ArticleDTO(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val author:String?,
    val content:String?,
    val description: String?,
    val publishedAt:String?,
    val source:Source?,
    val title: String?,
    val url:String?,
    val urtToImage:String?,
    )