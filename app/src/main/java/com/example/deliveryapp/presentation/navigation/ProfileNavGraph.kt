package com.example.deliveryapp.presentation.navigation

import BottomItemScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.deliveryapp.presentation.ui.holder.HolderScreen
import com.example.deliveryapp.presentation.ui.notifications.NotificationSettingsScreen
import com.example.deliveryapp.presentation.ui.profile.ProfileScreen
import com.example.deliveryapp.presentation.ui.sendpackage.SendPackageReceiptScreen
import com.example.deliveryapp.presentation.ui.sendpackage.SendPackageScreen
import com.example.deliveryapp.presentation.ui.sendpackage.SendPackageState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
            SendPackageScreen(instantDeliveryClick = { data ->
                val json = Json.encodeToString(data)
                navController.navigate("${ProfileScreen.SendPackageReceipt.route}/$json")
            })
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
        composable(
            route = "${ProfileScreen.SendPackageReceipt.route}/{data}",
            arguments = listOf(navArgument("data") { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            arguments.getString("data")?.let { detailsDataString ->
                val detailsData = Json.decodeFromString<SendPackageState>(detailsDataString)
                SendPackageReceiptScreen(
                    packageData = detailsData,
                    makePayment = { navController.navigate(ProfileScreen.Transaction.route) },
                    editPackage = { navController.navigateUp() }
                )
            }
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
    data object SendPackageReceipt : ProfileScreen(route = "send_package_receipt")
    data object Transaction : ProfileScreen(route = "transaction")
}