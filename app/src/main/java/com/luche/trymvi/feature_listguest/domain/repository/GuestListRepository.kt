package com.luche.trymvi.feature_listguest.domain.repository

import com.luche.trymvi.feature_savename.data.entity.Guest
import com.luche.trymvi.util.ResultStatus

interface GuestListRepository {
    suspend fun getGuestList(guestId: String) : ResultStatus<List<Guest>>
}