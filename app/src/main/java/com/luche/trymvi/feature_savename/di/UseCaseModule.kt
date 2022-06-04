package com.luche.trymvi.feature_savename.di

import com.luche.trymvi.feature_savename.domain.usecases.SaveNameUseCase
import com.luche.trymvi.feature_savename.domain.usecases.SaveNameUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val usecaseModule = module {
        factory<SaveNameUseCase> { SaveNameUseCaseImpl(saveNameRepository = get())}
    }
}