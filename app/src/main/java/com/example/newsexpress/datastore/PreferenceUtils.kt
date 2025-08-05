package com.example.newsexpress.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceUtils
    @Inject
    constructor(
        val dataStore: DataStore<Preferences>,
    ) {
        suspend fun saveString(
            key: String,
            value: String,
        ) {
            val prefKey = stringPreferencesKey(key)
            dataStore.edit { it ->
                it[prefKey] = value
            }
        }

        suspend fun getString(key: String): String? {
            val prefKey = stringPreferencesKey(key)
            return dataStore.data
                .map { it ->
                    it[prefKey]
                }.firstOrNull()
        }
    }
