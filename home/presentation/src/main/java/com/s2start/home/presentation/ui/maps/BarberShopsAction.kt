package com.s2start.home.presentation.ui.maps

sealed interface BarberShopsAction {
    data object OnLogoutClick: BarberShopsAction
}