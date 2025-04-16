package com.s2start.home.presentation.di

import com.s2start.home.presentation.ui.barbershops.create.BarberShopCreateViewModel
import com.s2start.home.presentation.ui.barbershops.detail.DetailsViewModel
import com.s2start.home.presentation.ui.barbershops.list.BarberShopsViewModel
import com.s2start.home.presentation.ui.chat.ChatViewModel
import com.s2start.home.presentation.ui.cut.CutViewModel
import com.s2start.home.presentation.ui.home.HomeViewModel
import com.s2start.home.presentation.ui.barbershops.mylist.MyBarberViewModel
import com.s2start.home.presentation.ui.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ChatViewModel)
    viewModelOf(::CutViewModel)
    viewModelOf(::BarberShopsViewModel)
    viewModelOf(::BarberShopCreateViewModel)
    viewModelOf(::MyBarberViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::DetailsViewModel)
}