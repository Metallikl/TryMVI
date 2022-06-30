package com.luche.trymvi

import android.app.Application
import com.luche.trymvi.feature_savename.di.DatabaseModule
import com.luche.trymvi.feature_savename.di.RepositoryModule
import com.luche.trymvi.feature_savename.di.UiModule
import com.luche.trymvi.feature_savename.di.UseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //
        startKoin {
            androidContext(this@App)
            //androidLogger(level = Level.DEBUG)
            modules(
                listOf(
                    DatabaseModule.databaseModule,
                    UiModule.mainModule,
                    UseCaseModule.usecaseModule,
                    RepositoryModule.repositoryModule
                )
            )
        }
    }
}