package com.example.deliveryapp.presentation.ui.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.data.repositories.DataStoreRepository
import com.example.deliveryapp.presentation.models.OnBoardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

    var state by mutableStateOf(OnBoardingState())
        private set

    fun loadData(onBoardingState: OnBoardingState) {
        state = onBoardingState
    }

    fun onNextButtonClick() {
        val currentPage = state.onBoardingQueue.poll()
        state = state.copy(
            currentPage = currentPage!!
        )
    }

    fun saveOnBoarding() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveOnBoarding()
        }
    }
}

data class OnBoardingState(
    val onBoardingQueue: Queue<OnBoardingPage> = LinkedList(
        listOf(
            OnBoardingPage.Second,
            OnBoardingPage.Third
        )
    ),
    val currentPage: OnBoardingPage = OnBoardingPage.First,
    val count: Int = 3,
)