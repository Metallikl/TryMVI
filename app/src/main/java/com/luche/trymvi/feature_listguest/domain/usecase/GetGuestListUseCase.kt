package com.luche.trymvi.feature_listguest.domain.usecase

import com.luche.trymvi.feature_savename.data.entity.Guest
import com.luche.trymvi.util.ResultStatus

interface GetGuestListUseCase {
    suspend operator fun invoke(guestId: String = ""): ResultStatus<List<Guest>>
}