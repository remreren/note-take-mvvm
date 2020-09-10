package com.bukonudakonusalim.takenotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bukonudakonusalim.takenotes.data.model.Setting

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(vararg settings: Setting)

    @Query("SELECT * FROM settings WHERE `key`=:key")
    fun getSetting(key: String): LiveData<Setting>
}