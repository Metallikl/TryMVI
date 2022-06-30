package com.luche.trymvi.feature_savename.domain.usecases

import com.luche.trymvi.feature_savename.domain.repository.SaveGuestRepository
import com.luche.trymvi.util.ResultStatus

class SaveGuestUseCaseImpl(
    private val saveGuestRepository: SaveGuestRepository
) : SaveGuestUseCase {
    override suspend fun invoke(name: String): ResultStatus<String> {
        return saveGuestRepository.saveGuest(name)
    }
}