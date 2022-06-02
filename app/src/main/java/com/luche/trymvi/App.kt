package com.luche.trymvi

import android.app.Application
import com.luche.trymvi.di.UiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //
        startKoin {
            androidContext(this@App)
            //androidLogger(level = Level.DEBUG)
            modules(listOf(UiModule.mainModule))
        }
    }
}