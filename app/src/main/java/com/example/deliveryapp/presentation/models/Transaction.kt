package com.example.deliveryapp.presentation.models

import java.util.Date

data class Transaction(
    val amount: Float,
    val date: Date,
    val description: String,
)