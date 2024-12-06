package com.s2start.auth.presentation.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.auth.domain.model.AccountModel
import com.s2start.auth.domain.usecase.CreateAccountUseCase
import com.s2start.tarefas.ui.onboarding.newaccount.RegisterState
import com.s2start.domain.util.ModelResult.Companion.onFailure
import com.s2start.domain.util.ModelResult.Companion.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val createAccountUseCase: CreateAccountUseCase):ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Default)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

     private fun createAccount(account:AccountModel){
         _registerState.value = RegisterState.Loading
         viewModelScope.launch {
            createAccountUseCase.invoke(account)
                .onSuccess {
                    Log.e("TAG", "createAccount: sucesso")
                }
                .onFailure {
                    _registerState.value = RegisterState.Error(it)
                }
        }
    }

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.CreateAccount -> createAccount(account = event.account)
        }
    }
}