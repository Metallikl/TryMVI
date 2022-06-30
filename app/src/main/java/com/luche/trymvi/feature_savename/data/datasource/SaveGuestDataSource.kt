package com.luche.trymvi.feature_savename.data.datasource

import com.luche.trymvi.feature_savename.data.entity.Guest

interface SaveGuestDataSource {
    suspend fun saveGuest(name: String) : Guest
}