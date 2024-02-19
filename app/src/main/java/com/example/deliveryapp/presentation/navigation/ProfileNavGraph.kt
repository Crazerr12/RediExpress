package com.example.deliveryapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.deliveryapp.presentation.ui.holder.HolderScreen
import com.example.deliveryapp.presentation.ui.notifications.NotificationSettingsScreen
import com.example.deliveryapp.presentation.ui.profile.ProfileScreen
import com.example.deliveryapp.presentation.ui.sendpackage.SendPackageScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        startDestination = BottomItemScreen.PROFILE.route,
        route = Graph.PROFILE
    ) {
        composable(route = BottomItemScreen.PROFILE.route) {
            ProfileScreen(
                editProfile = { navController.navigate(ProfileScreen.EditProfile.route) },
                openStatementsAndReports = { navController.navigate(ProfileScreen.StatementsAndReports.route) },
                openNotifications = { navController.navigate(ProfileScreen.NotificationSettings.route) },
                openCardAccountSettings = { navController.navigate(ProfileScreen.CardAndBankAccountSettings.route) },
                openReferrals = { navController.navigate(ProfileScreen.Referrals.route) },
                openAboutUs = { navController.navigate(ProfileScreen.AboutUs.route) },
                logOut = { navController.navigate(ProfileScreen.EditProfile.route) })
        }
        composable(route = ProfileScreen.EditProfile.route) {
            HolderScreen()
        }
        composable(route = ProfileScreen.StatementsAndReports.route) {
            SendPackageScreen()
        }
        composable(route = ProfileScreen.NotificationSettings.route) {
            NotificationSettingsScreen()
        }
        composable(route = ProfileScreen.CardAndBankAccountSettings.route) {
            HolderScreen()
        }
        composable(route = ProfileScreen.Referrals.route) {
            HolderScreen()
        }
        composable(route = ProfileScreen.AboutUs.route) {
            HolderScreen()
        }
        composable(route = Graph.AUTHENTICATION) {
            HolderScreen()
        }
    }
}

sealed class ProfileScreen(val route: String) {
    data object EditProfile : ProfileScreen(route = "edit_profile")
    data object StatementsAndReports : ProfileScreen(route = "statements_and_reports")
    data object NotificationSettings : ProfileScreen(route = "notification_settings")
    data object CardAndBankAccountSettings : ProfileScreen(route = "card_and_back_account_settings")
    data object Referrals : ProfileScreen(route = "referrals")
    data object AboutUs : ProfileScreen(route = "about_us")
}