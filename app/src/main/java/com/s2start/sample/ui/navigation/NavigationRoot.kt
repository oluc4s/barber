package com.s2start.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.s2start.auth.presentation.ui.login.LoginScreenRoot
import com.s2start.auth.presentation.ui.register.RegisterScreenRoot
import com.s2start.sample.data.model.Routes

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Auth
    ) { authGraph(navController) }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Routes.Auth>(
        startDestination = Routes.Login,
    ) {
        composable<Routes.Register> {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Register) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate(Routes.Login)
                }
            )
        }
        composable<Routes.Login> {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate(Routes.Run) {
                        popUpTo(Routes.Auth) {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navController.navigate(Routes.Register) {
                        popUpTo(Routes.Login) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}