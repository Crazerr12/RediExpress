package com.example.deliveryapp.domain.repositories

import com.example.deliveryapp.domain.models.User
import io.github.jan.supabase.gotrue.user.UserInfo

interface NetworkRepository {

    suspend fun registerUser(user: User)

    suspend fun logInUser(user: User)

    suspend fun signWithGoogle()

    suspend fun signOut()

    suspend fun retrieveUser(): UserInfo

    suspend fun modifyUser()

    suspend fun getUserBalance(): String

    suspend fun setUserBalance(balance: String): String
}