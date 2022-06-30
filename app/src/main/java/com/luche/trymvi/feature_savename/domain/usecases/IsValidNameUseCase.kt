package com.luche.trymvi.feature_savename.domain.usecases

import com.luche.trymvi.feature_savename.presentation.viewState.SaveGuestState

interface IsValidNameUseCase {
    suspend operator fun invoke(name: String): SaveGuestState.STATE
}