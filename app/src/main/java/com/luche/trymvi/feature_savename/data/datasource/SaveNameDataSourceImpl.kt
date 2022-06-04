package com.luche.trymvi.feature_savename.data.datasource

import kotlinx.coroutines.*

class SaveNameDataSourceImpl() : SaveNameDataSource {
    override suspend fun saveNameIntoServer(name: String): String {
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)
        }
        return name
    }
}