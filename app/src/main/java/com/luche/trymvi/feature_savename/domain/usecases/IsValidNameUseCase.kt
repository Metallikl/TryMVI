package com.luche.trymvi.feature_savename.domain.usecases

import com.luche.trymvi.feature_savename.presentation.viewState.SaveNameViewState

interface IsValidNameUseCase {
    suspend operator fun invoke(name: String): SaveNameViewState.STATE
}