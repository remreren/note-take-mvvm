package com.bukonudakonusalim.takenotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bukonudakonusalim.takenotes.data.model.Label

@Dao
interface LabelsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLabels(vararg labels: Label)

    @Query("SELECT * FROM labels")
    fun getLabels(): LiveData<List<Label>>
}