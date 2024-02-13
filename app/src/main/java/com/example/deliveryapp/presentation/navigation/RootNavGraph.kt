package com.example.deliveryapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.deliveryapp.presentation.ui.onboarding.OnBoardingViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
) {
    NavHost(
        route = Graph.Root,
        navController = navController,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(
            navController = navController,
            startDestination = startDestination,
            onBoardingViewModel = onBoardingViewModel
        )
        mainNavGraph(navController)
    }
}

fun NavHostController.navigate(
    route: String,
    popUpRoute: String,
    popUpInclusive: Boolean = false,
) {
    navigate(route) {
        if (popUpRoute.isNotEmpty()) {
            popUpTo(popUpRoute) {
                inclusive = popUpInclusive
            }
        }
    }
}

object Graph {
    const val Root = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}