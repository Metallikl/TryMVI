package com.luche.trymvi.feature_listguest.data.datasource

import com.luche.trymvi.feature_savename.data.entity.Guest

interface GuestListLocalDatabase {
    suspend fun getGuestList(guestId: String) : List<Guest>
}