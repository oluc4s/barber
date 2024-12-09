package com.s2start.core.database.di

import com.s2start.core.database.AppDatabase
import com.s2start.core.database.dao.UserDao
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> { AppDatabase.getDatabase(get()) }
    single<UserDao>{ get<AppDatabase>().userDao() }
}