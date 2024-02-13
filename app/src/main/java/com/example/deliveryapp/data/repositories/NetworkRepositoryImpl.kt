package com.example.deliveryapp.data.repositories

import com.example.deliveryapp.data.network.supabase.SupabaseClient.supabase
import com.example.deliveryapp.domain.models.User
import com.example.deliveryapp.domain.repositories.NetworkRepository
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class NetworkRepositoryImpl : NetworkRepository {
    override suspend fun registerUser(user: User) {
        supabase.auth.signUpWith(Email) {
            email = user.email
            password = user.password
            data = buildJsonObject {
                put("full_name", user.name)
                put("phone_number", user.phoneNumber)
            }
        }
    }

    override suspend fun logInUser(user: User){
        supabase.auth.signInWith(Email) {
            email = user.email
            password = user.password
        }
    }

    override suspend fun signWithGoogle() {
        supabase.auth.signInWith(Google)
    }
}