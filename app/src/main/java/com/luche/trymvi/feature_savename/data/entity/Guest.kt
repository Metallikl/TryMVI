package com.luche.trymvi.feature_savename.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "guest", indices = [Index(value = ["name"], unique = true)])
data class Guest(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int = 0,
    //@Index(unique = true,value = [name]
    @ColumnInfo(name = "name")val name:String
)
