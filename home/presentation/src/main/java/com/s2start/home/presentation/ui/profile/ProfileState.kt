package com.s2start.home.presentation.ui.profile

import com.s2start.domain.AuthInfo
import com.s2start.home.presentation.model.BarberResumeUi

data class ProfileState(
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = authInfo == null,
    val isHasBarber:Boolean = false
)