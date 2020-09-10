package com.bukonudakonusalim.takenotes.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bukonudakonusalim.takenotes.data.NotesDatabase
import com.bukonudakonusalim.takenotes.data.model.Note
import com.bukonudakonusalim.takenotes.data.repo.NotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application, notebookId: Long) : AndroidViewModel(application) {

    private val notesRepo: NotesRepo

    val allNotes: LiveData<List<Note>>

    init {
        val notesDao = NotesDatabase.getInstance(application).notesDao()
        notesRepo = NotesRepo(notesDao, notebookId)
        allNotes = notesRepo.notes
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) { notesRepo.insert(note) }
}