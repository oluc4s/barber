package com.s2start.home.data.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.s2start.home.domain.usecase.*
import com.s2start.home.domain.firebase.BarberFirebase
import com.s2start.home.data.firebase.BarberFirebaseImpl
import com.s2start.home.data.firebase.FirebaseModify
import com.s2start.home.data.repository.BarberRepositoryImpl
import com.s2start.home.domain.repository.BarberRepository

val homeDataModule = module {
    singleOf(::BarberFirebaseImpl).bind<BarberFirebase>()
    singleOf(::BarberRepositoryImpl).bind<BarberRepository>()
    singleOf(::CreateBarberUseCase)
    singleOf(::FirebaseModify)
    singleOf(::GetListBarberUseCase)
    single { Firebase.firestore }
}