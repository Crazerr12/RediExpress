package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.domain.repositories.NetworkRepository

class SignOutUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute() {
        networkRepository.signOut()
    }
}