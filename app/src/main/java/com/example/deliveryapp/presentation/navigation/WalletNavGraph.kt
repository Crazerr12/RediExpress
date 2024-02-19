package com.example.deliveryapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.deliveryapp.presentation.ui.holder.HolderScreen

fun NavGraphBuilder.walletNavGraph(navController: NavController) {
    navigation(
        startDestination = BottomItemScreen.WALLET.route,
        route = Graph.WALLET
    ) {
        composable(BottomItemScreen.WALLET.route) {
            HolderScreen()
        }
    }
}

sealed class WalletScreen(val route: String) {
    data object Wallet : WalletScreen(route = "edit_profile")
}