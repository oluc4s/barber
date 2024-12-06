package com.s2start.data.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.koin.dsl.module

val coreDataModule = module {
    single<FirebaseAuth> { Firebase.auth }
}