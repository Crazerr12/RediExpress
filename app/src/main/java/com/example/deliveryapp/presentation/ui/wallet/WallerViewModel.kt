package com.example.deliveryapp.presentation.ui.wallet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.domain.usecases.RetrieveUserUseCase
import com.example.deliveryapp.presentation.models.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallerViewModel @Inject constructor(
    val retrieveUserUseCase: RetrieveUserUseCase,

    ) : ViewModel() {

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    var state by mutableStateOf(WalletState())
        private set


    fun showBalance() {
        state = state.copy(
            balanceIsShow = !state.balanceIsShow
        )
    }

    private suspend fun getUser() {
        val name = retrieveUserUseCase.execute().userMetadata?.get("full_name").toString()
        state = state.copy(
            name = name.removeSurrounding("\""),
        )
    }
}

data class WalletState(
    val name: String = "",
    val balance: String = "",
    val balanceIsShow: Boolean = true,
    val history: MutableList<Transaction> = mutableStateListOf(),
)