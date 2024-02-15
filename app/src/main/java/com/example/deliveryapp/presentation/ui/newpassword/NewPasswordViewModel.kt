package com.example.deliveryapp.presentation.ui.newpassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.deliveryapp.presentation.common.checkPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(NewPasswordState())
        private set

    fun setPassword(password: String) {
        state = state.copy(
            password = password,
            confirmPasswordIsError = if (state.confirmPassword.isNotEmpty()) {
                password.checkPassword(state.confirmPassword)
            } else {
                false
            }
        )
        enableLogIn()
    }

    fun setConfirmPassword(password: String) {
        state = state.copy(
            confirmPassword = password,
            confirmPasswordIsError = state.password.checkPassword(password)
        )
        enableLogIn()
    }

    private fun enableLogIn() {
        if (state.password.isNotEmpty() && state.confirmPassword.isNotEmpty()
            && !state.confirmPasswordIsError
        ) {
            state = state.copy(
                logInIsEnabled = true
            )
        }
    }

}

data class NewPasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val confirmPasswordIsError: Boolean = true,
    val logInIsEnabled: Boolean = false,
)