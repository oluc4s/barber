package com.s2start.home.presentation.ui.barbershops.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.domain.SessionStorage
import com.s2start.domain.util.ModelResult.Companion.onSuccess
import com.s2start.home.domain.usecase.GetBarberItemUseCase
import com.s2start.home.domain.usecase.GetListBarberUseCase
import com.s2start.home.presentation.model.toUiListModel
import com.s2start.home.presentation.model.toUiModel
import com.s2start.home.presentation.ui.barbershops.mylist.MyBarberAction
import com.s2start.home.presentation.ui.barbershops.mylist.MyBarberState
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val sessionStorage: SessionStorage,
    private val getBarberItemUseCase: GetBarberItemUseCase,
    private val barberId:String,
): ViewModel() {
    var state by mutableStateOf(DetailsState())
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
            getBarberItemUseCase.invoke(barberId).onSuccess {
                state = state.copy(barberResumeUi = it.toUiModel(), isLoadding = false)
            }
        }
    }

    fun onAction(action: MyBarberAction) {
        when(action) {
            else -> Unit
        }
    }
}