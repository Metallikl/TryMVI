package com.luche.trymvi.feature_savename.data.datasource

interface SaveNameDataSource {
    suspend fun saveNameIntoServer(name: String) : String
}