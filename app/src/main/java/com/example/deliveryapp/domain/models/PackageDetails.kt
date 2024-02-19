package com.example.deliveryapp.domain.models

data class PackageDetails(
    val packageItems: List<String> = emptyList(),
    val weight: Float = 0f,
    val worthOfItems: Float = 0f,
)
