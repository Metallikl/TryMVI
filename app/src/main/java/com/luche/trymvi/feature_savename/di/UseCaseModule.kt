package com.luche.trymvi.feature_savename.di

import com.luche.trymvi.feature_savename.domain.usecases.IsValidNameUseCase
import com.luche.trymvi.feature_savename.domain.usecases.IsValidNameUseCaseImpl
import com.luche.trymvi.feature_savename.domain.usecases.SaveGuestUseCase
import com.luche.trymvi.feature_savename.domain.usecases.SaveGuestUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val usecaseModule = module {
        factory<SaveGuestUseCase> { SaveGuestUseCaseImpl(saveGuestRepository = get())}
        factory<IsValidNameUseCase> { IsValidNameUseCaseImpl()}
    }
}