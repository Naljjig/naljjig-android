package com.naljjig.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object LoginScreen : Routes

    @Serializable
    data object SignUpScreen : Routes

    @Serializable
    data object WelcomeScreen : Routes

    @Serializable
    data object HomeScreen : Routes
}