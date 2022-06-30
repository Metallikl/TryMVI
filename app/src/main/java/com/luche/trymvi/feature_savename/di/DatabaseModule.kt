package com.luche.trymvi.feature_savename.di

import androidx.room.Room
import com.luche.trymvi.database.AppDatabase
import com.luche.trymvi.database.dao.GuestDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
    val databaseModule = module {
        single<AppDatabase> {
            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java, "app_db"
            ).fallbackToDestructiveMigration()
            .build()
        }

        single<GuestDao> {
            val database = get<AppDatabase>()
            database.guestDao()
        }
    }
}