package com.s2start.home.presentation.ui.barbershops

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.domain.SessionStorage
import com.s2start.home.presentation.model.mockBarberResumeList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BarberShopsViewModel(
    private val applicationScope: CoroutineScope,
    private val sessionStorage: SessionStorage
): ViewModel() {
    var state by mutableStateOf(BarberShopsState())
        private set


    init {
        viewModelScope.launch {
            sessionStorage.get()?.let {
                state = state.copy(authInfo = it)
            }
            fetchBarberCoordinates()
        }
    }

    private fun fetchBarberCoordinates(){
        viewModelScope.launch {
            state = state.copy(barberResumeUi = mockBarberResumeList.toMutableList())
        }
    }


    private fun logout() {
        applicationScope.launch {
            sessionStorage.set(null)
        }
    }

    fun onAction(action: BarberShopsAction) {
        when(action) {
            BarberShopsAction.OnLogoutClick -> logout()
            else -> Unit
        }
    }
}