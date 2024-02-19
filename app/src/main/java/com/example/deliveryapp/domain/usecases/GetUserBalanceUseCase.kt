package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.domain.repositories.NetworkRepository

class GetUserBalanceUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute(): String =
        networkRepository.getUserBalance()
}