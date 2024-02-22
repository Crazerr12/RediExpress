package com.example.deliveryapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Details(
    val address: String = "",
    val country: String = "",
    val phoneNumber: String = "",
    val others: String = "",
)
