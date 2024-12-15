package com.naljjig.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naljjig.presentation.home.HomeScreen
import com.naljjig.presentation.login.LoginScreen
import com.naljjig.presentation.signup.SignUpScreen
import com.naljjig.presentation.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.WelcomeScreen
    ) {
        composable<Routes.WelcomeScreen> {
            WelcomeScreen(
                navigateToSignUp = { navController.navigate(Routes.SignUpScreen) },
                navigateToLogin = { navController.navigate(Routes.LoginScreen) }
            )
        }

        composable<Routes.LoginScreen> {
            LoginScreen(
                navigateToSignUp = { navController.navigate(Routes.SignUpScreen) },
                navigateToHome = { navController.navigate(Routes.HomeScreen) }
            )
        }

        composable<Routes.SignUpScreen> {
            SignUpScreen(
                navigateToLogin = { navController.navigate(Routes.LoginScreen) }
            )
        }

        composable<Routes.HomeScreen> {
            HomeScreen()
        }
    }
}