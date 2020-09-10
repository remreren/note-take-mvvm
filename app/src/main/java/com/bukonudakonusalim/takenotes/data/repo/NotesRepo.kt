package com.bukonudakonusalim.takenotes.data.repo

import androidx.lifecycle.LiveData
import com.bukonudakonusalim.takenotes.data.local.NotesDao
import com.bukonudakonusalim.takenotes.data.model.Note

class NotesRepo(private val notesDao: NotesDao, private val notebookId: Long) {
    val notes: LiveData<List<Note>> = notesDao.getNotebooks(notebookId)

    suspend fun insert(note: Note) {
        notesDao.insertNotes(note)
    }
}