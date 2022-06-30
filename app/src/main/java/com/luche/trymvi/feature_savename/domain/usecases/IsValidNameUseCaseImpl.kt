package com.luche.trymvi.feature_savename.domain.usecases

import com.luche.trymvi.feature_savename.presentation.viewState.SaveGuestState

class IsValidNameUseCaseImpl: IsValidNameUseCase {
    override suspend fun invoke(name: String): SaveGuestState.STATE {
        return if (name.isNotEmpty() && name.length > 4) {
            SaveGuestState.STATE.SUCCESS
        } else SaveGuestState.STATE.ERROR
    }
}