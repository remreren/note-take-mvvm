package com.bukonudakonusalim.takenotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bukonudakonusalim.takenotes.data.model.Notebook

@Dao
interface NotebooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotebook(vararg notebooks: Notebook)

    @Query("SELECT * FROM notebooks")
    fun getNotebooks(): LiveData<List<Notebook>>
}