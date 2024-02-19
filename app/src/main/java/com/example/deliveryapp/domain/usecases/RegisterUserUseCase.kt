package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.domain.models.User
import com.example.deliveryapp.domain.repositories.NetworkRepository

class RegisterUserUseCase(private val networkRepository: NetworkRepository) {
    suspend fun execute(user: User) {
        networkRepository.registerUser(user)
    }
}