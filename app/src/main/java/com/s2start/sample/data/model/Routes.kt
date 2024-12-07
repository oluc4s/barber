package com.s2start.sample.data.model

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Intro : Routes

    @Serializable
    data object Auth : Routes

    @Serializable
    data object AuthNavigation : Routes

    @Serializable
    data object Register : Routes

    @Serializable
    data object Login : Routes

    @Serializable
    data object HomeNavigation : Routes
    @Serializable
    data object HomeScreen : Routes
}