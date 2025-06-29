package com.example.newsexpress

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NewsDao {

    @Insert
    suspend fun insertArticle(articleDTO:ArticleDTO)

    @Delete
    suspend fun deleteArticle(articleDto:ArticleDTO)

    @Query("Select * from newsdb")
    suspend fun getListOfArticle(): List<ArticleDTO>



}