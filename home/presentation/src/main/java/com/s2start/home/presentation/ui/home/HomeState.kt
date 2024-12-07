package com.s2start.home.presentation.ui.home

import com.s2start.domain.AuthInfo

sealed class HomeState{
    object Loading : HomeState()
    data class UserData(val authInfo: AuthInfo) : HomeState()
}