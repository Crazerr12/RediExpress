package com.example.deliveryapp.presentation.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.domain.models.User
import com.example.deliveryapp.domain.usecases.RegisterUserUseCase
import com.example.deliveryapp.presentation.common.checkEmail
import com.example.deliveryapp.presentation.common.checkPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    fun setName(name: String) {
        state = state.copy(
            name = name
        )
        enableSignUp()
    }

    fun setPhone(phone: String) {
        state = state.copy(
            phoneNumber = phone
        )
        enableSignUp()
    }

    fun setEmail(email: String) {
        state = state.copy(
            email = email,
            emailIsError = !email.checkEmail()
        )
        enableSignUp()
    }

    fun setPassword(password: String) {
        state = state.copy(
            password = password,
            confirmedPasswordIsError = if (state.confirmedPassword.isNotEmpty()) {
                !password.checkPassword(state.confirmedPassword)
            } else {
                false
            }
        )
        enableSignUp()
    }

    fun setConfirmedPassword(password: String) {
        state = state.copy(
            confirmedPassword = password,
            confirmedPasswordIsError = !state.password.checkPassword(password)
        )
        enableSignUp()
    }

    fun setAgreement(isAgreed: Boolean) {
        state = state.copy(
            isAgreed = isAgreed
        )
        enableSignUp()
    }

    private fun enableSignUp() {
        state =
            if (state.name.isNotEmpty() && state.phoneNumber.isNotEmpty() && state.email.isNotEmpty()
                && state.password.isNotEmpty() && state.confirmedPassword.isNotEmpty()
                && state.isAgreed && !state.confirmedPasswordIsError && !state.emailIsError
            ) {
                state.copy(
                    signUpIsEnabled = true
                )
            } else {
                state.copy(
                    signUpIsEnabled = false
                )
            }
    }

    fun registerUser() {
        state = state.copy(
            isLoading = true,
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    registerUserUseCase.execute(
                        User(
                            email = state.email,
                            password = state.password,
                            name = state.name,
                            phoneNumber = state.phoneNumber
                        )
                    )
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
    }

    fun dismissError() {
        state = state.copy(
            error = null
        )
    }
}

data class SignUpState(
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val emailIsError: Boolean = false,
    val password: String = "",
    val confirmedPassword: String = "",
    val confirmedPasswordIsError: Boolean = false,
    val isAgreed: Boolean = false,
    val signUpIsEnabled: Boolean = false,
    val result: Exception? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)