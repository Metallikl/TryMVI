package com.luche.trymvi.feature_savename.domain.repository

import com.luche.trymvi.util.ResultStatus

interface SaveGuestRepository {
    suspend fun saveGuest(name: String) : ResultStatus<String>
}