package com.example.deliveryapp.presentation.ui.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.domain.models.User
import com.example.deliveryapp.domain.usecases.LogInUserUseCase
import com.example.deliveryapp.domain.usecases.SavePasswordUseCase
import com.example.deliveryapp.presentation.common.checkEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase,
    private val savePasswordUseCase: SavePasswordUseCase,
) :
    ViewModel() {

    var state by mutableStateOf(SignInState())
        private set

    fun setEmail(email: String) {
        state = state.copy(
            email = email,
            emailIsError = !email.checkEmail()
        )
        enableLogIn()
    }

    fun setPassword(password: String) {
        state = state.copy(
            password = password
        )
        enableLogIn()
    }

    fun rememberPassword(isRemember: Boolean) {
        state = state.copy(
            isRemember = isRemember
        )
    }

    private fun enableLogIn() {
        state =
            if (state.email.isNotEmpty() && state.password.isNotEmpty() && !state.emailIsError) {
                state.copy(
                    logInIsEnable = true
                )
            } else {
                state.copy(
                    logInIsEnable = false
                )
            }
    }

    fun logInUser() {
        state = state.copy(
            isLoading = true,
        )
        viewModelScope.launch {
            try {
                logInUserUseCase.execute(
                    User(
                        email = state.email,
                        password = state.password
                    )
                )
                if (state.isRemember) {
                    savePassword()
                }
                state = state.copy(
                    error = "successful",
                    isLoading = false,
                )
            } catch (e: Exception) {
                val startIndex = e.message!!.indexOf('(') + 1
                val endIndex = e.message!!.indexOf(')')
                state = state.copy(
                    isLoading = false,
                    error = e.message!!.substring(startIndex, endIndex)
                )
            }
        }
    }

    fun dismissError() {
        state = state.copy(
            error = null
        )
    }

    private fun savePassword() {
        viewModelScope.launch { savePasswordUseCase.execute(state.password) }
    }
}

data class SignInState(
    val email: String = "",
    val emailIsError: Boolean = false,
    val password: String = "",
    val isRemember: Boolean = false,
    val logInIsEnable: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)