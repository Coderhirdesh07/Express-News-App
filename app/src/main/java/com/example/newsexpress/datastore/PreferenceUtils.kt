package com.example.newsexpress.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class PreferenceUtils(val context: Context) {
    companion object {
        private val Context.datastore by preferencesDataStore("Local")
    }
    suspend fun saveString(key:String,value:String){
            context.datastore.edit {
                it[stringPreferencesKey(key)] = value
            }
    }

    suspend fun getString(key:String):String? {
        return context.datastore.data.map{
            it[stringPreferencesKey(key)]
        }.firstOrNull()
    }
}