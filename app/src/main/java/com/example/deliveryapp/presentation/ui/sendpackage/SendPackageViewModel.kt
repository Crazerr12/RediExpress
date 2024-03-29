package com.example.deliveryapp.presentation.ui.sendpackage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.deliveryapp.domain.models.Details
import com.example.deliveryapp.domain.models.PackageDetails
import com.example.deliveryapp.domain.usecases.GenerateTrackingNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.Serializable
import javax.inject.Inject

@HiltViewModel
class SendPackageViewModel @Inject constructor(
    private val generateTrackingNumberUseCase: GenerateTrackingNumberUseCase,
) : ViewModel() {
    var state by mutableStateOf(SendPackageState())
        private set

    fun getTrackingNumber() {
        state = state.copy(
            trackingNumber = generateTrackingNumberUseCase.execute()
        )
    }

    fun changeDetailsAddress(address: String) {
        state = state.copy(
            details = state.details.copy(
                address = address
            )
        )
    }

    fun changeDetailsCountry(country: String) {
        state = state.copy(
            details = state.details.copy(
                country = country
            )
        )
    }

    fun changeDetailsPhoneNumber(phoneNumber: String) {
        state = state.copy(
            details = state.details.copy(
                phoneNumber = phoneNumber
            )
        )
    }

    fun changeDetailsOthers(others: String) {
        state = state.copy(
            details = state.details.copy(
                others = others
            )
        )
    }

    fun addDestination() {
        state.destinationDetails.add(Details())
    }

    fun changeDestinationDetailsAddress(address: String, index: Int) {
        var item = state.destinationDetails[index]

        item = item.copy(
            address = address
        )

        state.destinationDetails[index] = item
    }

    fun changeDestinationDetailsCountry(country: String, index: Int) {
        var item = state.destinationDetails[index]

        item = item.copy(
            country = country
        )

        state.destinationDetails[index] = item
    }

    fun changeDestinationDetailsPhoneNumber(phoneNumber: String, index: Int) {
        var item = state.destinationDetails[index]

        item = item.copy(
            phoneNumber = phoneNumber
        )

        state.destinationDetails[index] = item
    }

    fun changeDestinationDetailsOthers(others: String, index: Int) {
        var item = state.destinationDetails[index]

        item = item.copy(
            others = others
        )

        state.destinationDetails[index] = item
    }

    fun changePackageItems(packageItems: String) {
        state = state.copy(
            packageDetails = state.packageDetails.copy(
                packageItems = packageItems
            )
        )
    }

    fun changePackageWorth(worthOfItems: String) {
        state = state.copy(

            packageDetails = state.packageDetails.copy(
                worthOfItems = worthOfItems.toFloat()
            )
        )
    }

    fun changePackageWeight(weight: String) {
        state = state.copy(
            packageDetails = state.packageDetails.copy(
                weight = weight.toFloat()
            )
        )
    }
}

@Serializable
data class SendPackageState(
    val details: Details = Details(),
    val destinationDetails: MutableList<Details> = mutableStateListOf(Details()),
    val packageDetails: PackageDetails = PackageDetails(),
    val trackingNumber: String = "",
)