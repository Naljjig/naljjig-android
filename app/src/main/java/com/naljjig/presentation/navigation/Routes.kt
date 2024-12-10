package com.naljjig.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    val route: String

    @Serializable
    data object LoginScreen : Routes {
        override val route = "log_in"
    }

    @Serializable
    data object SignUpScreen : Routes {
        override val route = "sign_up"
    }

    @Serializable
    data object WelcomeScreen : Routes {
        override val route = "my_page"
    }

    @Serializable
    data object HomeScreen : Routes {
        override val route = "home"
    }
}