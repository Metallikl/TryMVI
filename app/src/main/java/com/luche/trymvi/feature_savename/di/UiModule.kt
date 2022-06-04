package com.luche.trymvi.feature_savename.di

import com.luche.trymvi.feature_savename.presentation.viewModel.MainActViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModule {
    val mainModule = module {
        viewModel {
            MainActViewModel(saveNameUseCase = get())
        }
    }
}