package com.luche.trymvi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luche.trymvi.database.dao.GuestDao
import com.luche.trymvi.feature_savename.data.entity.Guest

@Database(
    entities = [Guest::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun guestDao(): GuestDao
}