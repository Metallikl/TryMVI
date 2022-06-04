package com.luche.trymvi.di

import com.luche.trymvi.datasource.SaveNameDataSource
import com.luche.trymvi.datasource.SaveNameDataSourceImpl
import com.luche.trymvi.repository.SaveNameRepository
import com.luche.trymvi.repository.SaveNameRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory <SaveNameRepository> { SaveNameRepositoryImpl(saveNameDataSource = get())}
        factory <SaveNameDataSource> { SaveNameDataSourceImpl()}
    }
}