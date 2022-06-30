package com.luche.trymvi.feature_savename.data.datasource

import com.luche.trymvi.database.dao.GuestDao
import com.luche.trymvi.feature_savename.data.entity.Guest
import kotlinx.coroutines.*

class SaveGuestLocalDataSourceImpl(
    private val guestDao: GuestDao
) : SaveGuestDataSource {
    override suspend fun saveGuest(name: String): Guest {
        var guest = Guest(name = name)
        return withContext(Dispatchers.IO) {
            guest = guestDao.getByName(name) ?: guest
            //
            guestDao.insertAll(
                guest
            )
            guestDao.getByName(name)
        }
    }
}