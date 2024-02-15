package com.example.deliveryapp

import com.example.deliveryapp.data.repositories.DataStoreRepository
import com.example.deliveryapp.presentation.models.OnBoardingPage
import com.example.deliveryapp.presentation.ui.onboarding.OnBoardingState
import com.example.deliveryapp.presentation.ui.onboarding.OnBoardingViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.LinkedList

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class OnBoardingViewModelTest {

    @Mock
    private lateinit var dataStoreRepository: DataStoreRepository

    private lateinit var onBoardingViewModel: OnBoardingViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        onBoardingViewModel = OnBoardingViewModel(dataStoreRepository)
    }

    @Test
    fun `Корректное извлечение элементов из очереди`() {
        val initialSize = onBoardingViewModel.state.onBoardingQueue.size

        onBoardingViewModel.onNextButtonClick()
        val newSize = onBoardingViewModel.state.onBoardingQueue.size

        assertEquals(initialSize - 1, newSize)
    }

    @Test
    fun `Изображения и текста из очереди извлекаются правильно`() {
        onBoardingViewModel.onNextButtonClick()

        assertEquals(onBoardingViewModel.state.currentPage, OnBoardingPage.Second)
    }
}