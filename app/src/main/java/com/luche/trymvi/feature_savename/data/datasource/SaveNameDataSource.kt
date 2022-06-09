package com.luche.trymvi.feature_savename.data.datasource

interface SaveNameDataSource {
    suspend fun saveName(name: String) : String
}