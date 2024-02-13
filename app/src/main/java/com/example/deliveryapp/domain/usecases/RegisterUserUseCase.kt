package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.domain.models.User
import com.example.deliveryapp.domain.repositories.NetworkRepository
import io.github.jan.supabase.gotrue.providers.builtin.Email

class RegisterUserUseCase(private val networkRepository: NetworkRepository) {
    suspend fun execute(user: User) {
        networkRepository.registerUser(user)
    }
}