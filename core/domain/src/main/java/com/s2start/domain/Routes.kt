package com.s2start.domain
import kotlinx.serialization.Serializable
sealed interface Routes {
    @Serializable
    data object AuthNavigation : Routes
    @Serializable
    data object RegisterScreen : Routes
    @Serializable
    data object LoginScreen : Routes
    @Serializable
    data object HomeScreen : Routes
    @Serializable
    data object ProfileScreen : Routes
    @Serializable
    data object ChatScreen : Routes
    @Serializable
    data object CutScreen : Routes
    @Serializable
    data object BarberShopsScreen : Routes
    @Serializable
    data object HomeNavigation: Routes
    @Serializable
    data object Recover: Routes
}