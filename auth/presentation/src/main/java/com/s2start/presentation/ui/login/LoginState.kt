package com.s2start.presentation.ui.login


sealed class LoginState{
    object Loading : LoginState()
    object Default : LoginState()
    data class Error(val e: Throwable) : LoginState()
}