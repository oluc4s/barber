package com.s2start.tarefas.ui.onboarding.newaccount


sealed class RegisterState{
    object Loading : RegisterState()
    object Default : RegisterState()
    data class Error(val e: Throwable) : RegisterState()
    data class EmailError(val message:String) : RegisterState()
}