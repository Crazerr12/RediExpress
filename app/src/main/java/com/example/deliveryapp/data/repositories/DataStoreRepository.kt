package com.example.deliveryapp.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val IS_ON_BOARDING_WATCH = "is_on_boarding_watch"
private const val PASSWORD = "password"

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun saveOnBoarding() {
        dataStore.edit { pref ->
            pref[booleanPreferencesKey(IS_ON_BOARDING_WATCH)] = true
        }
    }

    fun getOnBoarding() = dataStore.data.map { pref ->
        return@map pref[booleanPreferencesKey(IS_ON_BOARDING_WATCH)] ?: false
    }

    suspend fun savePassword(password: String) {
        dataStore.edit { pref ->
            pref[stringPreferencesKey(PASSWORD)] = password
        }
    }
}