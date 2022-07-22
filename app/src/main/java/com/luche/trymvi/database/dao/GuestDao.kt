package com.luche.trymvi.database.dao

import androidx.room.*
import com.luche.trymvi.feature_savename.data.entity.Guest

@Dao
interface GuestDao {
    @Query("SELECT * FROM guest")
    fun getAll(): List<Guest>

    @Query("SELECT * FROM guest WHERE name = :name")
    fun getByName(name: String): Guest

    @Query("SELECT * FROM guest WHERE name like '%'||:name||'%'")
    fun getByConstainsName(name: String): List<Guest>

    @Query("SELECT * FROM guest WHERE id like :id")
    fun getById(id: String): Guest

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg guest: Guest)

    @Delete
    suspend fun delete(guest: Guest)
}