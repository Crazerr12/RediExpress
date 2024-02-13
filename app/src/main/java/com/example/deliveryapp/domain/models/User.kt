package com.example.deliveryapp.domain.models

data class User(
    val email: String,
    val password: String,
    val name: String = "",
    val phoneNumber: String = "",
)
