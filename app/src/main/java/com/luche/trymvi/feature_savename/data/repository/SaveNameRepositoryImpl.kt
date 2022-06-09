package com.luche.trymvi.feature_savename.data.repository

import com.luche.trymvi.feature_savename.data.datasource.SaveNameDataSource
import com.luche.trymvi.feature_savename.domain.repository.SaveNameRepository
import com.luche.trymvi.util.ResultStatus
import java.lang.Exception

class SaveNameRepositoryImpl(
    private val saveNameDataSource: SaveNameDataSource
) : SaveNameRepository {
    override suspend fun saveNameIntoServer(name: String): ResultStatus<String> {
        return try {
            ResultStatus.Success(
                saveNameDataSource.saveName(name)
            )
        } catch (e: Exception) {
            ResultStatus.Error(
                e.message?:""
            )
        }
    }
}