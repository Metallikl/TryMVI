package com.luche.trymvi.feature_savename.domain.usecases

import com.luche.trymvi.feature_savename.presentation.viewState.SaveNameViewState

class IsValidNameUseCaseImpl: IsValidNameUseCase {
    override suspend fun invoke(name: String): SaveNameViewState.STATE {
        return if (name.isNotEmpty() && name.length > 4) {
            SaveNameViewState.STATE.SUCCESS
        } else SaveNameViewState.STATE.ERROR
    }
}