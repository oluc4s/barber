package com.s2start.home.presentation.ui.profile

sealed interface ProfileAction {
    data object OnLogoutClick: ProfileAction
}