package com.bukonudakonusalim.takenotes.data.model

import androidx.room.*
import com.bukonudakonusalim.takenotes.utils.DateConverter
import org.joda.time.DateTime

@Entity(tableName = "notebooks")
data class Notebook(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        val id: Long?,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "color", defaultValue = "0")
        val colorId: Int?,
        @ColumnInfo(name = "type", defaultValue = "1")
        val type: Int?,
        @ColumnInfo(name = "is_deleted", defaultValue = "0")
        val deleted: Boolean?,
        @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
        @TypeConverters(DateConverter::class)
        val createdAt: DateTime?,
        @ColumnInfo(name = "updated_at", defaultValue = "CURRENT_TIMESTAMP")
        @TypeConverters(DateConverter::class)
        val updatedAt: DateTime?,
) {
    @Ignore
    constructor(name: String, description: String, colorId: Int?) : this(null, name, description, colorId, null, null, null, null)
}