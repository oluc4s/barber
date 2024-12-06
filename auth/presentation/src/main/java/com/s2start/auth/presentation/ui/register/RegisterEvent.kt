package com.s2start.auth.presentation.ui.register

import com.s2start.auth.domain.model.AccountModel


sealed class RegisterEvent{
    data class CreateAccount(val account: AccountModel) : RegisterEvent()
}