package com.luche.trymvi.feature_listguest.data.datasource

import com.luche.trymvi.database.dao.GuestDao
import com.luche.trymvi.feature_savename.data.entity.Guest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GuestListLocalDatabaseImpl(
    private val guestDao: GuestDao
) : GuestListLocalDatabase {
    override suspend fun getGuestList(guestId: String): List<Guest> {
        return withContext(Dispatchers.IO) {
            if(guestId.isEmpty()){
                guestDao.getAll()
            }else{
                guestDao.getByConstainsName(guestId)
            }
        }
    }
}