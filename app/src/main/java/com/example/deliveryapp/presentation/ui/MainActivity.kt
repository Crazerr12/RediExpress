package com.example.deliveryapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.deliveryapp.presentation.theme.DeliveryAppTheme
import com.example.deliveryapp.presentation.ui.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoading.value
        }

        setContent {
            DeliveryAppTheme {
                val startDestination = viewModel.startDestination

                if (!viewModel.isLoading.collectAsState().value)
                    DeliveryApp(
                        startDestination = startDestination,
                    )
            }
        }
    }
}