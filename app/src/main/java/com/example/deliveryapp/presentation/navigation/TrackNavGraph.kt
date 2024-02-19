package com.example.deliveryapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.deliveryapp.presentation.ui.holder.HolderScreen

fun NavGraphBuilder.trackNavGraph(navController: NavController) {
    navigation(
        startDestination = BottomItemScreen.TRACK.route,
        route = Graph.TRACK
    ) {
        composable(BottomItemScreen.TRACK.route) {
            HolderScreen()
        }
    }
}

sealed class TrackScreen(val route: String) {
    data object Track : TrackScreen(route = "edit_profile")
}