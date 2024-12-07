package com.s2start.home.presentation.ui.home

sealed interface HomeAction {
    data object OnLogoutClick: HomeAction
}