package com.s2start.home.presentation.ui.barbershops.list

import com.s2start.domain.AuthInfo
import com.s2start.home.presentation.model.BarberResumeUi

data class BarberShopsState(
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = authInfo == null,
    val barberResumeUi:MutableList<BarberResumeUi> = mutableListOf()
)