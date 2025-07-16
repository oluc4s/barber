package com.s2start.home.presentation.ui.chat

sealed interface ChatAction {
    data object OnLogoutClick: ChatAction
}