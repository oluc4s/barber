package com.s2start.home.presentation.ui.cut

import com.s2start.domain.AuthInfo

data class CutState(
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = authInfo == null
)