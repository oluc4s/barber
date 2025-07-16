package com.s2start.home.presentation.route
import  kotlinx.serialization.Serializable

sealed interface HomeRoutes {
    @Serializable
    data object HomeScreen : HomeRoutes
    @Serializable
    data object ProfileScreen : HomeRoutes
    @Serializable
    data object ChatScreen : HomeRoutes
    @Serializable
    data object CutScreen : HomeRoutes
    @Serializable
    data object BarberShopsScreen : HomeRoutes
    @Serializable
    data class BarberDetailScreen(var barberId: String):HomeRoutes
    @Serializable
    data object MyBarberScreen : HomeRoutes
    @Serializable
    data object BarberShopsCreateScreen : HomeRoutes
    @Serializable
    data object HomeNavigation: HomeRoutes
}