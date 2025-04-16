package com.s2start.home.presentation.ui.barbershops.detail

import com.s2start.domain.AuthInfo
import com.s2start.home.presentation.model.BarberResumeUi

data class DetailsState(
    val barberResumeUi:BarberResumeUi? = null,
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = barberResumeUi == null
)