package com.luche.trymvi.repository

import com.luche.trymvi.datasource.SaveNameDataSource
import com.luche.trymvi.util.ResultStatus
import java.lang.Exception

class SaveNameRepositoryImpl(
    private val saveNameDataSource: SaveNameDataSource
) : SaveNameRepository {
    override suspend fun saveNameIntoServer(name: String): ResultStatus<String> {
        return try {
            ResultStatus.Success(
                saveNameDataSource.saveNameIntoServer(name)
            )
        } catch (e: Exception) {
            ResultStatus.Error(
                e.message?:""
            )
        }
    }
}