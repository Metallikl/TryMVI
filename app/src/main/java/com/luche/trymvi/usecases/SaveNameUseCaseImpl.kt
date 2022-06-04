package com.luche.trymvi.usecases

import com.luche.trymvi.repository.SaveNameRepository
import com.luche.trymvi.util.ResultStatus

class SaveNameUseCaseImpl(
    private val saveNameRepository: SaveNameRepository
) : SaveNameUseCase {
    override suspend fun invoke(name: String): ResultStatus<String> {
        return saveNameRepository.saveNameIntoServer(name)
    }
}