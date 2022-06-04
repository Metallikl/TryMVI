package com.luche.trymvi.usecases

import com.luche.trymvi.util.ResultStatus

interface SaveNameUseCase {
    suspend operator fun invoke(name: String) : ResultStatus<String>
}