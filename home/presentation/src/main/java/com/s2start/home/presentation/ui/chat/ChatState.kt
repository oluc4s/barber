package com.s2start.home.presentation.ui.chat

import com.s2start.domain.AuthInfo

data class ChatState(
    val authInfo: AuthInfo? = null,
    val isLoadding:Boolean = authInfo == null
)