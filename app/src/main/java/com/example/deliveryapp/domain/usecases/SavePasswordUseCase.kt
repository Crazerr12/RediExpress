package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.data.repositories.DataStoreRepository
import com.example.deliveryapp.presentation.common.hashData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SavePasswordUseCase(private val dataStoreRepository: DataStoreRepository) {

    suspend fun execute(password: String) {
        val hashedPassword = CoroutineScope(Dispatchers.Default).async {
            hashData(password.toByteArray())
        }

        dataStoreRepository.savePassword(hashedPassword.await())
    }
}