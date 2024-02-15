package com.example.deliveryapp.presentation.ui.forgotpassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.deliveryapp.presentation.common.checkEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(ForgotPasswordState())
        private set

    fun setEmail(email: String) {
        state = state.copy(
            email = email,
            emailIsError = email.checkEmail()
        )
        enableSend()
    }

    private fun enableSend() {
        if (!state.emailIsError && state.email.isNotEmpty()) {
            state = state.copy(
                sendIsEnable = true
            )
        }
    }
}

data class ForgotPasswordState(
    val email: String = "",
    val emailIsError: Boolean = false,
    val sendIsEnable: Boolean = false,
)