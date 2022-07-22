package com.luche.trymvi.feature_listguest.domain.usecase

import com.luche.trymvi.feature_listguest.domain.repository.GuestListRepository
import com.luche.trymvi.feature_savename.data.entity.Guest
import com.luche.trymvi.util.ResultStatus

class GetGuestListUseCaseImpl(
    private val repository: GuestListRepository
) : GetGuestListUseCase {
    override suspend fun invoke(guestId: String): ResultStatus<List<Guest>> {
        return repository.getGuestList(guestId)
    }
}