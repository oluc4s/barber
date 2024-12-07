package com.s2start.sample

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import com.s2start.core.data.di.coreDataModule
import com.s2start.auth.presentation.di.authViewModelModule
import com.s2start.auth.data.di.authDataModule
import com.s2start.core.presentation.ui.BuildConfig
import com.s2start.home.presentation.di.homePresentationModule
import com.s2start.sample.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SampleApp: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@SampleApp)
            modules(
                authDataModule,
                authViewModelModule,
                coreDataModule,
                appModule,
                homePresentationModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}