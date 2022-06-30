package com.luche.trymvi.feature_savename.domain.usecases

import com.luche.trymvi.util.ResultStatus

interface SaveGuestUseCase {
    suspend operator fun invoke(name: String) : ResultStatus<String>
}