package com.luche.trymvi.feature_savename.di

import com.luche.trymvi.feature_savename.data.datasource.SaveGuestDataSource
import com.luche.trymvi.feature_savename.data.datasource.SaveGuestLocalDataSourceImpl
import com.luche.trymvi.feature_savename.data.datasource.SaveGuestRemoteDataSourceImpl
import com.luche.trymvi.feature_savename.domain.repository.SaveGuestRepository
import com.luche.trymvi.feature_savename.data.repository.SaveGuestRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory<SaveGuestRepository> { SaveGuestRepositoryImpl(saveGuestDataSource = get()) }
        factory<SaveGuestDataSource> {
            //SaveGuestRemoteDataSourceImpl()
            SaveGuestLocalDataSourceImpl(guestDao = get())
        }
    }
}