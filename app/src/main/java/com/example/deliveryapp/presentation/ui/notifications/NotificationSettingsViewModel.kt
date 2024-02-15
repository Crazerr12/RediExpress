package com.example.deliveryapp.presentation.ui.notifications

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationSettingsViewModel @Inject constructor() : ViewModel() {

}

data class NotificationState(
    val notify: Boolean = false,
)