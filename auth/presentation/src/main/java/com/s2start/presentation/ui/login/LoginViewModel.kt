package com.s2start.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.core.ModelResult.Companion.onFailure
import com.s2start.core.ModelResult.Companion.onSuccess
import com.s2start.core.data.manager.DataStoreManager
import com.s2start.core.data.usecase.LoginUseCase
import com.s2start.core.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val dataStoreManager:DataStoreManager
):ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Default)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

     private fun login(email:String,password:String){
         _loginState.value = LoginState.Loading
        viewModelScope.launch {
            loginUseCase.invoke(email,password)
                .onSuccess {
                    //session start to home
                }
                .onFailure { _loginState.value = LoginState.Error(it) }
        }
    }
    private fun noWantLogin(){
        viewModelScope.launch {
            dataStoreManager.setValue(Constants.DataStore.NOWANTLOGIN, true)
        }
    }


    fun onEvent(event:LoginEvent){
        when(event){
            is LoginEvent.Login -> login(email = event.email, password = event.password)
            is LoginEvent.NoWantLogin -> noWantLogin()

        }
    }
}