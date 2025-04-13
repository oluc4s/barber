package com.s2start.home.presentation.ui.profile

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.s2start.domain.SessionStorage
import com.s2start.domain.util.ModelResult.Companion.onSuccess
import com.s2start.home.domain.model.BarberModel
import com.s2start.home.domain.usecase.CreateBarberUseCase
import com.s2start.home.domain.usecase.GetListBarberUseCase
import com.s2start.home.domain.usecase.GetMyListBarberUseCase
import com.s2start.home.presentation.model.BarberResumeUi
import com.s2start.home.presentation.model.mockBarberResumeList
import com.s2start.home.presentation.model.toUiListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class ProfileViewModel(
    private val applicationScope: CoroutineScope,
    private val sessionStorage: SessionStorage,
    private val getMyListBarberUseCase: GetMyListBarberUseCase
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
        getMyListBarberUseCase.invoke().onSuccess{
            state = state.copy(isHasBarber = it.isNotEmpty())
        }
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