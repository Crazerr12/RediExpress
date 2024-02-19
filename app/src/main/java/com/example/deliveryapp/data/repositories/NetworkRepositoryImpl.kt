package com.example.deliveryapp.data.repositories

import com.example.deliveryapp.data.network.supabase.SupabaseClient.supabase
import com.example.deliveryapp.domain.models.User
import com.example.deliveryapp.domain.repositories.NetworkRepository
import io.github.jan.supabase.gotrue.SignOutScope
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
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

    override suspend fun logInUser(user: User) {
        supabase.auth.signInWith(Email) {
            email = user.email
            password = user.password
        }
    }

    override suspend fun signWithGoogle() {
        supabase.auth.signInWith(Google)
    }

    override suspend fun signOut() {
        supabase.auth.signOut(SignOutScope.GLOBAL)
    }

    override suspend fun retrieveUser(): UserInfo {
        return supabase.auth.retrieveUserForCurrentSession(updateSession = true)
    }

    override suspend fun modifyUser() {
        supabase.auth.modifyUser {
            email = ""
        }
    }

    override suspend fun getUserBalance(): String {
        return supabase.from("balances").select(columns = Columns.list("balance")) {
            filter {
                eq("id", retrieveUser().id)
            }
            limit(1)
            single()
        }.toString()
    }

    override suspend fun setUserBalance(balance: String): String {
        val id = retrieveUser().id
        return supabase.from("balances")
            .insert(mapOf("id" to id, "balance" to balance)) {
                select()
                single()
            }.toString()
    }
}