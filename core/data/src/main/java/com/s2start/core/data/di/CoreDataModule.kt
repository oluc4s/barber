package com.s2start.core.data.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.s2start.domain.SessionStorage
import com.s2start.core.data.auth.EncryptedSessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single<FirebaseAuth> { Firebase.auth }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}