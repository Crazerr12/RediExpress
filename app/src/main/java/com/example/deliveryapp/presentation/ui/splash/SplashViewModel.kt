package com.example.deliveryapp.presentation.ui.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.data.network.supabase.SupabaseClient
import com.example.deliveryapp.data.repositories.DataStoreRepository
import com.example.deliveryapp.presentation.navigation.AuthScreen
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    var startDestination by mutableStateOf(AuthScreen.SignUp.route)
        private set

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            dataStoreRepository.getOnBoarding().take(1).collect { isShowed ->
                startDestination = if (isShowed) {
                    AuthScreen.SignUp.route
                } else {
                    AuthScreen.OnBoarding.route
                }
                _isLoading.value = false
            }
        }
    }
}