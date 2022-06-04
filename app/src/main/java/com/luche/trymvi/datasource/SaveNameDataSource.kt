package com.luche.trymvi.datasource

interface SaveNameDataSource {
    suspend fun saveNameIntoServer(name: String) : String
}