package com.example.deliveryapp

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.deliveryapp.data.repositories.DataStoreRepository
import com.example.deliveryapp.di.dataStore
import com.example.deliveryapp.presentation.models.OnBoardingPage
import com.example.deliveryapp.presentation.navigation.AuthScreen
import com.example.deliveryapp.presentation.navigation.RootNavGraph
import com.example.deliveryapp.presentation.ui.onboarding.OnBoardingScreen
import com.example.deliveryapp.presentation.ui.onboarding.OnBoardingState
import com.example.deliveryapp.presentation.ui.onboarding.OnBoardingViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.LinkedList

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class OnBoardingUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val context = ApplicationProvider.getApplicationContext<Context>()
    lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var onBoardingViewModel: OnBoardingViewModel

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        dataStoreRepository = DataStoreRepository(context.dataStore)
        onBoardingViewModel = OnBoardingViewModel(dataStoreRepository)
    }

    @Test
    fun WhenQueueIsNotEmptySetNextButton() {
        val onBoardingState = OnBoardingState(
            onBoardingQueue = LinkedList(
                listOf(
                    OnBoardingPage.Second,
                    OnBoardingPage.Third,
                )
            ),
            currentPage = OnBoardingPage.First,
            count = 3
        )
        onBoardingViewModel.loadData(onBoardingState)
        composeTestRule.setContent {
            OnBoardingScreen(signIn = {}, signUp = {}, vm = onBoardingViewModel)
        }

        composeTestRule.onNodeWithTag("Sign In").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("Next").assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithTag("Sign In").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("Next").assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithTag("Next").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("Sign In").assertIsDisplayed()
    }

    @Test
    fun WhenQueueIsEmptyTheButtonTextEqualsSignUp() {
        val onBoardingState = OnBoardingState(
            onBoardingQueue = LinkedList(
                listOf()
            ),
            currentPage = OnBoardingPage.Third,
            count = 1
        )
        onBoardingViewModel.loadData(onBoardingState)

        composeTestRule.setContent {
            OnBoardingScreen(signIn = {}, signUp = {}, vm = onBoardingViewModel)
        }
        composeTestRule.onNodeWithTag("Next").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("Sign In").assertIsDisplayed()
    }

    @Test
    fun WhenQueueIsEmptyAndUserClickSignInDisplayHolder() {
        val onBoardingState = OnBoardingState(
            onBoardingQueue = LinkedList(
                listOf()
            ),
            currentPage = OnBoardingPage.Third,
            count = 1
        )
        onBoardingViewModel.loadData(onBoardingState)

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            RootNavGraph(
                navController = navController,
                startDestination = AuthScreen.OnBoarding.route,
                onBoardingViewModel = onBoardingViewModel,
            )
        }

        composeTestRule.onNodeWithTag("Next").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("Sign In").assertIsDisplayed().performClick()

        assertEquals(AuthScreen.SignUp.route, navController.currentBackStackEntry?.destination?.route)
    }
}