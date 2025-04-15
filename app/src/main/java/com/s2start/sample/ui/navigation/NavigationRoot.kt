package com.s2start.sample.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.s2start.auth.presentation.route.AuthRoutes
import com.s2start.auth.presentation.ui.login.LoginScreenRoot
import com.s2start.auth.presentation.ui.recover.RecoverScreenRoot
import com.s2start.auth.presentation.ui.register.RegisterScreenRoot
import com.s2start.home.presentation.ui.home.HomeScreenRoot
import com.s2start.home.presentation.route.HomeRoutes
import com.s2start.home.presentation.ui.barbershops.create.BarberShopCreateScreenRoot
import com.s2start.home.presentation.ui.barbershops.list.BarberShopsScreenRoot
import com.s2start.home.presentation.ui.barbershops.mylist.MyBarberScreenRoot
import com.s2start.home.presentation.ui.chat.ChatScreenRoot
import com.s2start.home.presentation.ui.cut.CutScreenRoot
import com.s2start.home.presentation.ui.barbershops.detail.DetailScreenRoot
import com.s2start.home.presentation.ui.profile.ProfileScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) HomeRoutes.HomeNavigation else AuthRoutes.AuthNavigation
    ) {
        authGraph(navController)
        homeGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<AuthRoutes.AuthNavigation>(
        startDestination = AuthRoutes.LoginScreen,
    ) {
        composable<AuthRoutes.RegisterScreen>(
            enterTransition = { slideIntoContainer(SlideDirection.Start, animationSpec = tween(500)) },
            exitTransition = { slideOutOfContainer(SlideDirection.Right, animationSpec = tween(500)) }
        ) {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate(AuthRoutes.LoginScreen) {
                        popUpTo(AuthRoutes.RegisterScreen) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate(AuthRoutes.LoginScreen)
                }
            )
        }
        composable<AuthRoutes.LoginScreen> {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate(HomeRoutes.HomeNavigation) {
                        popUpTo(AuthRoutes.AuthNavigation) {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navController.navigate(AuthRoutes.RegisterScreen) {
                        popUpTo(AuthRoutes.LoginScreen) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onRecoverClick = {
                    navController.navigate(AuthRoutes.Recover) {
                        popUpTo(AuthRoutes.LoginScreen) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }

            )
        }
        composable<AuthRoutes.Recover>(
            enterTransition = { slideIntoContainer(SlideDirection.Start, animationSpec = tween(500)) },
            exitTransition = { slideOutOfContainer(SlideDirection.Right, animationSpec = tween(500)) }
        ) {
            RecoverScreenRoot(
                onSignInClick = {
                    navController.navigate(AuthRoutes.LoginScreen) {
                        popUpTo(AuthRoutes.RegisterScreen) {
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
    navigation<HomeRoutes.HomeNavigation>(
        startDestination = HomeRoutes.HomeScreen
    ) {
        composable<HomeRoutes.HomeScreen> {
            HomeScreenRoot(
                onNavigate = {
                    navController.navigate(it)
                }
            )
        }
        composable<HomeRoutes.ProfileScreen> {
            ProfileScreenRoot(
                onNavigate = {
                    navController.navigate(it)
                },
                onLogoutClick = {
                    navController.navigate(AuthRoutes.AuthNavigation) {
                        popUpTo(HomeRoutes.HomeNavigation) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<HomeRoutes.BarberShopsCreateScreen> {
            BarberShopCreateScreenRoot(
                onNavigate = { navController.navigate(it) },
                onBack = { navController.popBackStack() }
            )
        }
        composable<HomeRoutes.ChatScreen> {
            ChatScreenRoot(onNavigate = { navController.navigate(it) })
        }
        composable<HomeRoutes.CutScreen> {
            CutScreenRoot(onNavigate = { navController.navigate(it) })
        }
        composable<HomeRoutes.BarberShopsScreen> {
            BarberShopsScreenRoot(onNavigate = { navController.navigate(it) })
        }
        composable<HomeRoutes.MyBarberScreen>(
            enterTransition = { slideIntoContainer(SlideDirection.Start, animationSpec = tween(500)) },
            exitTransition = { slideOutOfContainer(SlideDirection.Right, animationSpec = tween(500)) }
        ) {
            MyBarberScreenRoot(
                onNavigate = { navController.navigate(it) },
                onBack = { navController.popBackStack() }
            )
        }
        composable<HomeRoutes.BarberDetailScreen>(
            enterTransition = { slideIntoContainer(SlideDirection.Start, animationSpec = tween(500)) },
            exitTransition = { slideOutOfContainer(SlideDirection.Right, animationSpec = tween(500)) }
        ) {
            val args = it.toRoute<HomeRoutes.BarberDetailScreen>()
            DetailScreenRoot(
                onBack = { navController.popBackStack() }
            )
        }
    }
}