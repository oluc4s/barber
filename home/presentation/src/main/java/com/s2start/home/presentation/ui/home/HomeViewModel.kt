package com.s2start.home.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.s2start.domain.SessionStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(
    private val applicationScope: CoroutineScope,
    private val sessionStorage: SessionStorage
): ViewModel() {
    private fun logout() {
        applicationScope.launch {
            sessionStorage.set(null)
        }
    }

    fun onAction(action: HomeAction) {
        when(action) {
            HomeAction.OnLogoutClick -> logout()
            else -> Unit
        }
    }
}