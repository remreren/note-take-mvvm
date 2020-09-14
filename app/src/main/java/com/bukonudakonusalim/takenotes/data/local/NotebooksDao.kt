package com.bukonudakonusalim.takenotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bukonudakonusalim.takenotes.data.model.Notebook

@Dao
interface NotebooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotebook(vararg notebooks: Notebook)

    @Delete
    suspend fun deleteNotebook(vararg notebooks: Notebook)

    @Query("SELECT * FROM notebooks WHERE id=:id LIMIT 1")
    fun getNotebookById(id: Long): LiveData<Notebook>

    @Query("SELECT * FROM notebooks")
    fun getNotebooks(): LiveData<List<Notebook>>
}