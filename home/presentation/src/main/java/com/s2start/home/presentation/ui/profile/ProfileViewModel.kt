package com.s2start.home.presentation.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.domain.SessionStorage
import com.s2start.home.presentation.model.SessionConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val applicationScope: CoroutineScope,
    private val sessionStorage: SessionStorage,
): ViewModel() {
    var state by mutableStateOf(ProfileState())
        private set


    init {
        viewModelScope.launch {
            sessionStorage.get()?.let {
                state = state.copy(authInfo = it)
            }
            CheckBarberList()
        }
    }

    private suspend fun CheckBarberList(){

    }

    private fun logout() {
        applicationScope.launch {
            sessionStorage.set(null)
        }
    }


    fun onAction(action: ProfileAction) {
        when(action) {
            ProfileAction.OnLogoutClick -> logout()
            else -> Unit
        }
    }
}