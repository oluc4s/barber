package com.s2start.home.presentation.ui.barbershops.mylist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.domain.SessionStorage
import com.s2start.domain.util.ModelResult.Companion.onSuccess
import com.s2start.home.domain.usecase.GetListBarberUseCase
import com.s2start.home.domain.usecase.GetMyListBarberUseCase
import com.s2start.home.presentation.model.toUiListModel
import kotlinx.coroutines.launch

class MyBarberViewModel(
    private val sessionStorage: SessionStorage,
    private val getMyListBarberUseCase: GetMyListBarberUseCase
): ViewModel() {
    var state by mutableStateOf(MyBarberState())
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
            getMyListBarberUseCase.invoke().onSuccess {
                state = state.copy(barberResumeUi = it.toUiListModel().toMutableList())
            }
        }
    }

    fun onAction(action: MyBarberAction) {
        when(action) {
            else -> Unit
        }
    }
}