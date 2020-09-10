package com.bukonudakonusalim.takenotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bukonudakonusalim.takenotes.data.model.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(vararg notes: Note)

    @Query("SELECT * FROM notes WHERE parent_id =:id")
    fun getNotebooks(id: Long): LiveData<List<Note>>
}