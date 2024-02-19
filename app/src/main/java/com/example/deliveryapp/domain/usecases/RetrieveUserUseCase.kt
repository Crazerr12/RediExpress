package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.domain.repositories.NetworkRepository
import io.github.jan.supabase.gotrue.user.UserInfo

class RetrieveUserUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute(): UserInfo =
        networkRepository.retrieveUser()
}