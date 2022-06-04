package com.luche.trymvi.feature_savename.domain.repository

import com.luche.trymvi.util.ResultStatus

interface SaveNameRepository {
    suspend fun saveNameIntoServer(name: String) : ResultStatus<String>
}