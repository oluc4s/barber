package com.s2start.home.presentation.di

import com.s2start.home.presentation.ui.chat.ChatViewModel
import com.s2start.home.presentation.ui.cut.CutViewModel
import com.s2start.home.presentation.ui.home.HomeViewModel
import com.s2start.home.presentation.ui.maps.MapViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ChatViewModel)
    viewModelOf(::CutViewModel)
    viewModelOf(::MapViewModel)
}