package com.s2start.home.presentation.ui.cut

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.domain.SessionStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CutViewModel(
    private val applicationScope: CoroutineScope,
    private val sessionStorage: SessionStorage
): ViewModel() {
    var state by mutableStateOf(CutState())
        private set


    init {
        viewModelScope.launch {
            sessionStorage.get()?.let {
                state = state.copy(authInfo = it)
            }
        }
    }



    private fun logout() {
        applicationScope.launch {
            sessionStorage.set(null)
        }
    }

    fun onAction(action: CutAction) {
        when(action) {
            CutAction.OnLogoutClick -> logout()
            else -> Unit
        }
    }
}