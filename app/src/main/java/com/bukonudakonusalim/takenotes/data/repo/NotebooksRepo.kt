package com.bukonudakonusalim.takenotes.data.repo

import androidx.lifecycle.LiveData
import com.bukonudakonusalim.takenotes.data.local.NotebooksDao
import com.bukonudakonusalim.takenotes.data.model.Notebook

class NotebooksRepo(private val notebooksDao: NotebooksDao) {
    val notebooks: LiveData<List<Notebook>> = notebooksDao.getNotebooks()

    suspend fun delete(notebook:Notebook) {
        notebooksDao.deleteNotebook(notebook)
    }

    suspend fun insert(notebook:Notebook) {
        notebooksDao.insertNotebook(notebook)
    }
}