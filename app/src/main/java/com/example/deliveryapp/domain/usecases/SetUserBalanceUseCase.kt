package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.domain.repositories.NetworkRepository

class SetUserBalanceUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute(balance: String): String =
        networkRepository.setUserBalance(balance)
}