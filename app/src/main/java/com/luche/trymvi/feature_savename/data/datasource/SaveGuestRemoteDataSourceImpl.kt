package com.luche.trymvi.feature_savename.data.datasource

import com.luche.trymvi.feature_savename.data.entity.Guest
import kotlinx.coroutines.*
import java.util.*
import kotlin.random.Random

class SaveGuestRemoteDataSourceImpl() : SaveGuestDataSource {
    override suspend fun saveGuest(name: String): Guest {
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)
        }
        return Guest(
            id = Random.nextInt(0,100),
            name = name
        )
    }
}