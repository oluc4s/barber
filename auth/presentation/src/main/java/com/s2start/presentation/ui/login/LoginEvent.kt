package com.s2start.presentation.ui.login

sealed class LoginEvent{
    data class Login(val email: String,val password:String) : LoginEvent()
    object NoWantLogin: LoginEvent()
}