package com.bukonudakonusalim.takenotes.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bukonudakonusalim.takenotes.ui.main.MainViewModel
import com.bukonudakonusalim.takenotes.ui.notebook.NotebookViewModel

class ViewModelProviderFactory(private var application: Application, private var notebookId: Long?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (notebookId != null && modelClass.isAssignableFrom(NotebookViewModel::class.java)) {
            return NotebookViewModel(application, notebookId!!) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        }
        return modelClass as T
    }
}