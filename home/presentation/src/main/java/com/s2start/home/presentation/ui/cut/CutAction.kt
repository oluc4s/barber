package com.s2start.home.presentation.ui.cut

sealed interface CutAction {
    data object OnLogoutClick: CutAction
}