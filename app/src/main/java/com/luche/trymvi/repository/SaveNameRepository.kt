package com.luche.trymvi.repository

import com.luche.trymvi.util.ResultStatus

interface SaveNameRepository {
    suspend fun saveNameIntoServer(name: String) : ResultStatus<String>
}