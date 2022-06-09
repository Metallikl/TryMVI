package com.luche.trymvi.feature_savename.di

import com.luche.trymvi.feature_savename.presentation.viewModel.SaveNameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModule {
    val mainModule = module {
        viewModel {
            SaveNameViewModel(saveNameUseCase = get(), isValidNameUseCase = get())
        }
    }
}