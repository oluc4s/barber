package com.s2start.home.presentation.ui.home

import androidx.compose.runtime.MutableState
import com.s2start.domain.AuthInfo
import com.s2start.home.presentation.model.BarberResumeUi
import com.s2start.home.presentation.model.BarberUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeState(
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = authInfo == null,
    val barberResumeUi:MutableList<BarberResumeUi> = mutableListOf()
)