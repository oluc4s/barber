package com.s2start.sample.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.s2start.auth.presentation.ui.login.LoginScreenRoot
import com.s2start.auth.presentation.ui.register.RegisterScreenRoot
import com.s2start.sample.data.model.Routes

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Routes.HomeNavigation else Routes.AuthNavigation
    ) {
        authGraph(navController)
        homeGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Routes.AuthNavigation>(
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
                    navController.navigate(Routes.HomeNavigation) {
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



private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<Routes.HomeNavigation>(
        startDestination = Routes.HomeScreen
    ) {
        composable<Routes.HomeScreen> {
           Text("asdsadsa")
        }
    }
}