package com.luche.trymvi.di

import com.luche.trymvi.usecases.SaveNameUseCase
import com.luche.trymvi.usecases.SaveNameUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val usecaseModule = module {
        factory<SaveNameUseCase> { SaveNameUseCaseImpl(saveNameRepository = get())}
    }
}