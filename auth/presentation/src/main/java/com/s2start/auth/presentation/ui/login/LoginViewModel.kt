package com.s2start.auth.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.auth.domain.usecase.LoginUseCase
import com.s2start.domain.util.ModelResult.Companion.onFailure
import com.s2start.domain.util.ModelResult.Companion.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase):ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Default)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val eventChannel = Channel<LoginNotification>()
    val events = eventChannel.receiveAsFlow()

     private fun login(email:String,password:String){
         _loginState.value = LoginState.Loading
        viewModelScope.launch {
            loginUseCase.invoke(email,password)
                .onSuccess { sendChannel(LoginNotification.LoginSuccess) }
                .onFailure { _loginState.value = LoginState.Error(it) }
        }
    }
    private fun noWantLogin(){
        viewModelScope.launch {
//            dataStoreManager.setValue(Constants.DataStore.NOWANTLOGIN, true)
        }
    }

    private fun sendChannel(notification: LoginNotification){
        viewModelScope.launch {
            eventChannel.send(notification)
        }
    }


    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.Login -> login(email = event.email, password = event.password)
            is LoginEvent.NoWantLogin -> noWantLogin()
        }
    }
}