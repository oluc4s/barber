package com.s2start.home.presentation.ui.barbershops.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.domain.SessionStorage
import com.s2start.domain.util.ModelResult.Companion.onFailure
import com.s2start.domain.util.ModelResult.Companion.onSuccess
import com.s2start.home.domain.model.BarberModel
import com.s2start.home.domain.usecase.CreateBarberUseCase
import com.s2start.home.domain.usecase.GetListBarberUseCase
import com.s2start.home.presentation.model.toUiListModel
import com.s2start.home.presentation.ui.home.HomeAction
import kotlinx.coroutines.launch

class BarberShopCreateViewModel(
    private val sessionStorage: SessionStorage,
    private val createBarberUseCase: CreateBarberUseCase
): ViewModel() {
    var state by mutableStateOf(BarberShopCreateState())
        private set

    init {
        viewModelScope.launch {
            sessionStorage.get()?.let {
                state = state.copy(authInfo = it)
            }
        }
    }

    private fun createBarber(account: BarberModel){
        viewModelScope.launch {
            createBarberUseCase.invoke(account).onSuccess {
                state = state.copy(createUser = true)
            }.onFailure {
                state = state.copy(createUser = false)
            }
        }
    }

    fun onAction(action: BarberShopCreateAction) {
        when(action) {
            is BarberShopCreateAction.CreateBarber -> createBarber(action.account)
            else -> Unit
        }
    }
}