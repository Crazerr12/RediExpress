package com.example.deliveryapp.data.network.supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://citaekcpzzczcypufqva.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNpdGFla2NwenpjemN5cHVmcXZhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDc0NjMyMzAsImV4cCI6MjAyMzAzOTIzMH0.uLQWPxK5syPO8sXmEuSRAySuPbpx-8qhokZDJuNm-dg",
    ) {
        install(Postgrest)
        install(Auth)
    }
}