package com.luche.trymvi.di

import com.luche.trymvi.viewModel.MainActViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModule {
    val mainModule = module {
        viewModel {
            MainActViewModel(saveNameUseCase = get())
        }
    }
}