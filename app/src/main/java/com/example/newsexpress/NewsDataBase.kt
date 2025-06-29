package com.example.newsexpress

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [],version = 1)
abstract class NewsDataBase(): RoomDatabase(){
    abstract fun getNewsDao(): NewsDao
}