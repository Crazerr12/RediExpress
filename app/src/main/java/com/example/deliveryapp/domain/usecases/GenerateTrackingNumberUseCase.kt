package com.example.deliveryapp.domain.usecases

import com.example.deliveryapp.presentation.common.generateTrackNumber

class GenerateTrackingNumberUseCase {
    fun execute(): String = generateTrackNumber()
}