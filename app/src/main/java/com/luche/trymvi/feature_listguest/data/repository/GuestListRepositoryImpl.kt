package com.luche.trymvi.feature_listguest.data.repository

import com.luche.trymvi.feature_listguest.data.datasource.GuestListLocalDatabase
import com.luche.trymvi.feature_listguest.domain.repository.GuestListRepository
import com.luche.trymvi.feature_savename.data.entity.Guest
import com.luche.trymvi.util.ResultStatus

class GuestListRepositoryImpl(
    private val guestListLocalDatabase: GuestListLocalDatabase
): GuestListRepository {
    override suspend fun getGuestList(guestId: String): ResultStatus<List<Guest>> {
        return try {
            ResultStatus.Success(
                data = guestListLocalDatabase.getGuestList(guestId)
            )
        }catch (e: Exception){
            ResultStatus.Error(e.toString())
        }
    }
}