package com.s2start.data.di

import com.s2start.data.firebase.AuthFirebase
import com.s2start.data.firebase.AuthFirebaseImpl
import com.s2start.data.repository.AuthRepositoryImpl
import com.s2start.domain.repository.AuthRepository
import com.s2start.domain.usecase.LoginUseCase

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

val authDataModule = module {
    singleOf(::AuthFirebaseImpl).bind<AuthFirebase>()
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::LoginUseCase)
}