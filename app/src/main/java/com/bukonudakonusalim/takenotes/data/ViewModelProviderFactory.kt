package com.bukonudakonusalim.takenotes.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bukonudakonusalim.takenotes.data.viewmodel.MainViewModel
import com.bukonudakonusalim.takenotes.data.viewmodel.NotebooksViewModel
import com.bukonudakonusalim.takenotes.data.viewmodel.NotesViewModel

class ViewModelProviderFactory(private var application: Application, private var notebookId: Long?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (notebookId != null && modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(application, notebookId!!) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        } else if (modelClass.isAssignableFrom(NotebooksViewModel::class.java)) {
            return NotebooksViewModel(application) as T
        }
        return modelClass as T
    }
}