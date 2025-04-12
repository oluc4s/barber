package com.s2start.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.s2start.auth.presentation.ui.login.LoginScreenRoot
import com.s2start.auth.presentation.ui.recover.RecoverScreenRoot
import com.s2start.auth.presentation.ui.register.RegisterScreenRoot
import com.s2start.home.presentation.ui.home.HomeScreenRoot
import com.s2start.domain.Routes
import com.s2start.home.presentation.ui.barbershops.create.BarberShopCreateScreenRoot
import com.s2start.home.presentation.ui.chat.ChatScreenRoot
import com.s2start.home.presentation.ui.cut.CutScreenRoot
import com.s2start.home.presentation.ui.barbershops.list.BarberShopsScreenRoot
import com.s2start.home.presentation.ui.profile.ProfileScreenRoot

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
        startDestination = Routes.LoginScreen,
    ) {
        composable<Routes.RegisterScreen> {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate(Routes.LoginScreen) {
                        popUpTo(Routes.RegisterScreen) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate(Routes.LoginScreen)
                }
            )
        }
        composable<Routes.LoginScreen> {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate(Routes.HomeNavigation) {
                        popUpTo(Routes.AuthNavigation) {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navController.navigate(Routes.RegisterScreen) {
                        popUpTo(Routes.LoginScreen) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onRecoverClick = {
                    navController.navigate(Routes.Recover) {
                        popUpTo(Routes.LoginScreen) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }

            )
        }
        composable<Routes.Recover> {
            RecoverScreenRoot(
                onSignInClick = {
                    navController.navigate(Routes.LoginScreen) {
                        popUpTo(Routes.RegisterScreen) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onRecoverSuccess = {}
            )
        }
    }
}



private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<Routes.HomeNavigation>(
        startDestination = Routes.HomeScreen
    ) {
        composable<Routes.HomeScreen> {
            HomeScreenRoot(
                onNavigate = {
                    navController.navigate(it)
                }
            )
        }
        composable<Routes.ProfileScreen> {
            ProfileScreenRoot(
                onNavigate = {
                    navController.navigate(it)
                },
                onLogoutClick = {
                    navController.navigate(Routes.AuthNavigation) {
                        popUpTo(Routes.HomeNavigation) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Routes.BarberShopsCreateScreen> {
            BarberShopCreateScreenRoot(
                onNavigate = { navController.navigate(it) },
                onBack = { navController.popBackStack() }
            )
        }
        composable<Routes.ChatScreen> {
            ChatScreenRoot(onNavigate = { navController.navigate(it) })
        }
        composable<Routes.CutScreen> {
            CutScreenRoot(onNavigate = { navController.navigate(it) })
        }
        composable<Routes.BarberShopsScreen> {
            BarberShopsScreenRoot(onNavigate = { navController.navigate(it) })
        }
    }
}