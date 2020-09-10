package com.bukonudakonusalim.takenotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bukonudakonusalim.takenotes.data.local.LabelsDao
import com.bukonudakonusalim.takenotes.data.local.NotebooksDao
import com.bukonudakonusalim.takenotes.data.local.NotesDao
import com.bukonudakonusalim.takenotes.data.local.SettingsDao
import com.bukonudakonusalim.takenotes.data.model.Label
import com.bukonudakonusalim.takenotes.data.model.Note
import com.bukonudakonusalim.takenotes.data.model.Notebook
import com.bukonudakonusalim.takenotes.data.model.Setting
import com.bukonudakonusalim.takenotes.utils.DateConverter

@Database(entities = [Note::class, Notebook::class, Label::class, Setting::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
    abstract fun notebooksDao(): NotebooksDao
    abstract fun labelsDao(): LabelsDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var instance: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomInstance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "notes_database.db"
                ).build()
                instance = roomInstance
                return roomInstance
            }
        }
    }
}