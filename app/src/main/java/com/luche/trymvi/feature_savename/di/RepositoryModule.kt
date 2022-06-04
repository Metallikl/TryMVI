package com.luche.trymvi.feature_savename.di

import com.luche.trymvi.feature_savename.data.datasource.SaveNameDataSource
import com.luche.trymvi.feature_savename.data.datasource.SaveNameDataSourceImpl
import com.luche.trymvi.feature_savename.domain.repository.SaveNameRepository
import com.luche.trymvi.feature_savename.data.repository.SaveNameRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory <SaveNameRepository> { SaveNameRepositoryImpl(saveNameDataSource = get())}
        factory <SaveNameDataSource> { SaveNameDataSourceImpl()}
    }
}