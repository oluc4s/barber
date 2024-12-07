package com.s2start.sample.data.model

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
    data object HomeNavigation: Routes
    @Serializable
    data object Recover: Routes
}