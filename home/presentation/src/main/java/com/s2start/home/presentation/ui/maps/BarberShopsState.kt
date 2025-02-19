package com.s2start.home.presentation.ui.maps

import com.s2start.domain.AuthInfo

data class BarberShopsState(
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = authInfo == null
)