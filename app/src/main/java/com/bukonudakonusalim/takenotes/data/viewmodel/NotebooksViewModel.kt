package com.bukonudakonusalim.takenotes.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bukonudakonusalim.takenotes.data.NotesDatabase
import com.bukonudakonusalim.takenotes.data.local.NotebooksDao
import com.bukonudakonusalim.takenotes.data.model.Notebook
import com.bukonudakonusalim.takenotes.data.repo.NotebooksRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotebooksViewModel(application: Application) : AndroidViewModel(application) {

    val notebooksDao: NotebooksDao = NotesDatabase.getInstance(application).notebooksDao()
    private val notebooksRepo: NotebooksRepo

    val allNotebooks: LiveData<List<Notebook>>

    init {
        notebooksRepo = NotebooksRepo(notebooksDao)
        allNotebooks = notebooksRepo.notebooks
    }

    fun deleteNotebook(notebook: Notebook) = viewModelScope.launch(Dispatchers.IO) { notebooksRepo.delete(notebook) }
    fun insertNotebook(notebook: Notebook) = viewModelScope.launch(Dispatchers.IO) { notebooksRepo.insert(notebook) }
}