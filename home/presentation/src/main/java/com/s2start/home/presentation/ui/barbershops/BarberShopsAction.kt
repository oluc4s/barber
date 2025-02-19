package com.s2start.home.presentation.ui.barbershops

sealed interface BarberShopsAction {
    data object OnLogoutClick: BarberShopsAction
}