package com.example.deliveryapp.presentation.ui.profile

import android.media.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

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
}

data class ProfileState(
    val name: String = "",
    val photo: Image? = null,
    val currentBalance: Int = 0,
    val balanceIsShow: Boolean = true,
    val darkModeIsEnable: Boolean = false,
)