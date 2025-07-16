package com.s2start.home.presentation.ui.home

import com.s2start.domain.AuthInfo
import com.s2start.home.presentation.model.BarberResumeUi

data class HomeState(
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = authInfo == null,
    val barberResumeUi:MutableList<BarberResumeUi> = mutableListOf()
)