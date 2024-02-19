package com.example.deliveryapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

fun NavGraphBuilder.bottomNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Graph.HOME,
        route = Graph.BOTTOM
    ) {
        homeNavGraph(navController)
        walletNavGraph(navController)
        trackNavGraph(navController)
        profileNavGraph(navController)
    }
}