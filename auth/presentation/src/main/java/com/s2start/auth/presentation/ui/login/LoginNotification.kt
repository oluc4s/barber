package com.s2start.auth.presentation.ui.login

sealed class LoginNotification{
    data object LoginSuccess: LoginNotification()
}