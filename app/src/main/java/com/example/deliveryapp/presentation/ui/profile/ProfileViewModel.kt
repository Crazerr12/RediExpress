package com.example.deliveryapp.presentation.ui.profile

import android.media.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.domain.usecases.GetUserBalanceUseCase
import com.example.deliveryapp.domain.usecases.RetrieveUserUseCase
import com.example.deliveryapp.domain.usecases.SetUserBalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val setUserBalanceUseCase: SetUserBalanceUseCase,
    private val getUserBalanceUseCase: GetUserBalanceUseCase,
    private val retrieveUserUseCase: RetrieveUserUseCase,
) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    fun showBalance() {
        state = state.copy(
            balanceIsShow = !state.balanceIsShow
        )
    }

    fun enableDarkMode() {
        state = state.copy(
            darkModeIsEnable = !state.darkModeIsEnable
        )
    }

    private suspend fun getUser() {
        val name = retrieveUserUseCase.execute().userMetadata?.get("full_name").toString()
        state = state.copy(
            name = name.removeSurrounding("\""),
        )
    }
}

data class ProfileState(
    val name: String = "",
    val photo: Image? = null,
    val currentBalance: Int = 0,
    val balanceIsShow: Boolean = true,
    val darkModeIsEnable: Boolean = false,
)