package com.achmadichzan.dicodingevents.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DarkThemePreferences private constructor(internal val dataStore: DataStore<Preferences>) {

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[THEME_KEY] == true
        }

    suspend fun saveThemeSetting(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDark
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DarkThemePreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): DarkThemePreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = DarkThemePreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}