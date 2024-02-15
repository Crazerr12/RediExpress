package com.example.deliveryapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.deliveryapp.data.repositories.DataStoreRepository
import com.example.deliveryapp.data.repositories.NetworkRepositoryImpl
import com.example.deliveryapp.domain.repositories.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATA_STORE = "data_store"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE)

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context,
    ) = DataStoreRepository(dataStore = context.dataStore)

    @Provides
    @Singleton
    fun provideNetworkRepository(): NetworkRepository {
        return NetworkRepositoryImpl()
    }
}