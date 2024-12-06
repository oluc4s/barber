package com.s2start.auth.data.di

import com.s2start.data.firebase.AuthFirebase
import com.s2start.auth.data.firebase.AuthFirebaseImpl
import com.s2start.auth.data.repository.AuthRepositoryImpl
import com.s2start.auth.domain.repository.AuthRepository
import com.s2start.auth.domain.usecase.*
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

val authDataModule = module {
    singleOf(::AuthFirebaseImpl).bind<AuthFirebase>()
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::LoginUseCase)
    singleOf(::CreateAccountUseCase)

}