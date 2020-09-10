package com.bukonudakonusalim.takenotes.data.model

import androidx.room.*

@Entity(
        tableName = "settings",
        indices = [
            Index(
                    value = ["key"],
                    unique = true
            )
        ]
)
data class Setting(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        val id: Long?,
        @ColumnInfo(name = "key")
        val key: String,
        @ColumnInfo(name = "value")
        val value: String
) {
    @Ignore
    constructor(key: String, value: String) : this(null, key, value)
}