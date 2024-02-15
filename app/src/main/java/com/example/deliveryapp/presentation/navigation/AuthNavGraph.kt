package com.example.deliveryapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.deliveryapp.presentation.ui.forgotpassword.ForgotPasswordScreen
import com.example.deliveryapp.presentation.ui.holder.HolderScreen
import com.example.deliveryapp.presentation.ui.newpassword.NewPasswordScreen
import com.example.deliveryapp.presentation.ui.onboarding.OnBoardingScreen
import com.example.deliveryapp.presentation.ui.onboarding.OnBoardingViewModel
import com.example.deliveryapp.presentation.ui.signin.SignInScreen
import com.example.deliveryapp.presentation.ui.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    startDestination: String,
    onBoardingViewModel: OnBoardingViewModel,
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = startDestination
    ) {
        composable(route = AuthScreen.OnBoarding.route) {
            OnBoardingScreen(
                signIn = {
                    navController.navigate(
                        AuthScreen.SignUp.route,
                        AuthScreen.OnBoarding.route,
                        true
                    )
                    navController.navigate(AuthScreen.SignIn.route)
                },
                signUp = {
                    navController.navigate(
                        AuthScreen.SignUp.route,
                        AuthScreen.OnBoarding.route,
                        true
                    )
                },
                vm = onBoardingViewModel
            )
        }
        composable(route = AuthScreen.SignIn.route) {
            SignInScreen(
                signIn = { navController.navigate(Graph.BOTTOM, AuthScreen.SignUp.route, true) },
                signUp = { navController.navigateUp() },
                forgotPassword = { navController.navigate(AuthScreen.ForgotPassword.route) },
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(
                signIn = { navController.navigate(AuthScreen.SignIn.route) },
            )
        }
        composable(route = AuthScreen.ForgotPassword.route) {
            ForgotPasswordScreen(
                sendOTP = { navController.navigate(AuthScreen.OTPVerification.route) },
                signIn = { navController.navigateUp() })
        }
        composable(route = AuthScreen.OTPVerification.route) {
            HolderScreen()
        }
        composable(route = AuthScreen.NewPassword.route) {
            NewPasswordScreen(signIn = {
                navController.navigate(
                    route = AuthScreen.SignIn.route,
                    popUpRoute = AuthScreen.SignIn.route,
                )
            })
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object OnBoarding : AuthScreen(route = "on_boarding")
    data object SignIn : AuthScreen(route = "sign_in")
    data object SignUp : AuthScreen(route = "sign_up")
    data object Home : AuthScreen(route = "home")
    data object ForgotPassword : AuthScreen(route = "forgot_password")
    data object NewPassword : AuthScreen(route = "new_password")
    data object OTPVerification : AuthScreen(route = "otp_verification")
}