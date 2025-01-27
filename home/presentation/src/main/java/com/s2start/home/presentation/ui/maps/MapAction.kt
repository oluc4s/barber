package com.s2start.home.presentation.ui.maps

sealed interface MapAction {
    data object OnLogoutClick: MapAction
}