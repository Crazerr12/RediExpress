package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.domain.repositories.NetworkRepository

class SignWithGoogleUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute() {
        networkRepository.signWithGoogle()
    }
}