package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.domain.models.User
import com.example.deliveryapp.domain.repositories.NetworkRepository

class LogInUserUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute(user: User) {
        networkRepository.logInUser(user)
    }
}