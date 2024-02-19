package com.example.deliveryapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.deliveryapp.presentation.ui.holder.HolderScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        startDestination = BottomItemScreen.HOME.route,
        route = Graph.HOME
    ) {
       composable(BottomItemScreen.HOME.route){
           HolderScreen()
       }
    }
}

sealed class HomeScreen(val route: String) {
    data object Home : HomeScreen(route = "edit_profile")
    data object SecondHome : HomeScreen(route = "edit_profile")
}