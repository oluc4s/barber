package com.s2start.auth.presentation.ui.login


sealed class LoginState{
    object Loading : LoginState()
    object Default : LoginState()
    data class Error(val e: Throwable) : LoginState()
}