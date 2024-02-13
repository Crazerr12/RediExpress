package com.example.deliveryapp.domain.repositories

import com.example.deliveryapp.domain.models.User

interface NetworkRepository {

    suspend fun registerUser(user: User)

    suspend fun logInUser(user: User)

    suspend fun signWithGoogle()
}