package com.bukonudakonusalim.takenotes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labels")
data class Label(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        val id: Long?,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "color_start")
        val colorStart: String,
        @ColumnInfo(name = "color_end")
        val colorEnd: String,
)