package com.luche.trymvi.feature_savename.di

import com.luche.trymvi.feature_savename.domain.usecases.IsValidNameUseCase
import com.luche.trymvi.feature_savename.domain.usecases.IsValidNameUseCaseImpl
import com.luche.trymvi.feature_savename.domain.usecases.SaveNameUseCase
import com.luche.trymvi.feature_savename.domain.usecases.SaveNameUseCaseImpl
import get
import org.koin.dsl.module

object UseCaseModule {
    val usecaseModule = module {
        factory<SaveNameUseCase> { SaveNameUseCaseImpl(saveNameRepository = get())}
        factory<IsValidNameUseCase> { IsValidNameUseCaseImpl()}
    }
}