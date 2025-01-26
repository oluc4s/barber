package com.s2start.home.presentation.ui.home

import com.s2start.domain.AuthInfo

data class HomeState(
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = authInfo == null
)