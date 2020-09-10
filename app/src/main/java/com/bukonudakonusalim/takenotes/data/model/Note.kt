package com.bukonudakonusalim.takenotes.data.model

import androidx.room.*
import com.bukonudakonusalim.takenotes.utils.DateConverter
import org.joda.time.DateTime

@Entity(tableName = "notes")
data class Note(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        val id: Long?,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "content")
        val content: String,
        @ColumnInfo(name = "parent_id")
        val parentId: Long,
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
    constructor(title: String, content: String, parentId: Long) : this(null, title, content, parentId, null, null, null)
}