package com.example.deliveryapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.ui.holder.HolderScreen
import com.example.deliveryapp.presentation.ui.profile.ProfileScreen

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    navigation(
        startDestination = BottomItemScreen.HOME.route,
        route = Graph.BOTTOM
    ) {
        composable(route = BottomItemScreen.HOME.route) {
            HolderScreen()
        }
        composable(route = BottomItemScreen.WALLET.route) {
            HolderScreen()
        }
        composable(route = BottomItemScreen.TRACK.route) {
            HolderScreen()
        }
        composable(route = BottomItemScreen.PROFILE.route) {
            ProfileScreen(
                editProfile = { /*TODO*/ },
                openStatementsAndReports = { /*TODO*/ },
                openNotifications = { /*TODO*/ },
                openCardAccountSettings = { /*TODO*/ },
                openReferrals = { /*TODO*/ },
                openAboutUs = { /*TODO*/ },
                logOut = { /*TODO*/ })
        }
    }
}

enum class BottomItemScreen(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
) {
    HOME(title = R.string.home_item, R.drawable.home_item, "home_item"),
    WALLET(title = R.string.wallet_item, R.drawable.wallet_item, "wallet_item"),
    TRACK(title = R.string.track_item, R.drawable.track_item, "track_item"),
    PROFILE(title = R.string.profile_item, R.drawable.profile_item, "profile_item"),
}