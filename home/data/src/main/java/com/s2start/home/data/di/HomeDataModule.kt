package com.s2start.home.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.s2start.home.domain.usecase.*
import com.s2start.home.domain.firebase.BarberFirebase
import com.s2start.home.data.firebase.BarberFirebaseImpl
import com.s2start.home.data.repository.BarberRepositoryImpl
import com.s2start.home.domain.repository.BarberRepository

val homeDataModule = module {
    singleOf(::BarberFirebaseImpl).bind<BarberFirebase>()
    singleOf(::BarberRepositoryImpl).bind<BarberRepository>()
    singleOf(::CreateBarberUseCase)
    singleOf(::GetListBarberUseCase)
}