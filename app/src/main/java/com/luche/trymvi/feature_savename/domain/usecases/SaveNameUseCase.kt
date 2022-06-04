package com.luche.trymvi.feature_savename.domain.usecases

import com.luche.trymvi.util.ResultStatus

interface SaveNameUseCase {
    suspend operator fun invoke(name: String) : ResultStatus<String>
}