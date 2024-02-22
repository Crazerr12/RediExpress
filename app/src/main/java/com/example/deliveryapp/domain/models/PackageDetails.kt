package com.example.deliveryapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class PackageDetails(
    val packageItems: String = "",
    val weight: Float = 0f,
    val worthOfItems: Float = 0f,
)
