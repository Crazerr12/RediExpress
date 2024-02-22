package com.example.deliveryapp.presentation.ui.sendpackage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.deliveryapp.domain.models.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SendPackageReceiptViewModel @Inject constructor(
) : ViewModel() {

    var state by mutableStateOf(SendPackageReceiptState())
        private set

    fun receiveData(data: SendPackageState) {
        setDeliveryCharges(data.destinationDetails.toList())
        setTax()
    }

    private fun setDeliveryCharges(details: List<Details>) {
        state = state.copy(
            deliveryCharges = 2500 * details.count()
        )
    }

    private fun setPackageTotal(){
        state = state.copy(
           // packageTotal = state
        )
    }
    private fun setTax() {
        state = state.copy(
            taxAndServiceCharges = (state.deliveryCharges + state.instantDelivery) * (5 / 100).toFloat()
        )
    }
}

data class SendPackageReceiptState(
    val trackingNumber: String = "",
    val deliveryCharges: Int = 2500,
    val instantDelivery: Int = 300,
    val taxAndServiceCharges: Float = 0f,
    val packageTotal: Float = 0f,
)