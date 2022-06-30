package com.luche.trymvi.feature_savename.data.repository

import com.luche.trymvi.feature_savename.data.datasource.SaveGuestDataSource
import com.luche.trymvi.feature_savename.domain.repository.SaveGuestRepository
import com.luche.trymvi.util.ResultStatus
import java.lang.Exception

class SaveGuestRepositoryImpl(
    private val saveGuestDataSource: SaveGuestDataSource
) : SaveGuestRepository {
    override suspend fun saveGuest(name: String): ResultStatus<String> {
        return try {
            val guest = saveGuestDataSource.saveGuest(name)
            ResultStatus.Success(
                guest.name
            )
        } catch (e: Exception) {
            ResultStatus.Error(
                e.message?:""
            )
        }
    }
}