package com.bukonudakonusalim.takenotes.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bukonudakonusalim.takenotes.data.NotesDatabase
import com.bukonudakonusalim.takenotes.data.local.SettingsDao
import com.bukonudakonusalim.takenotes.data.model.Setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsDao: SettingsDao

    init {
        settingsDao = NotesDatabase.getInstance(application).settingsDao()
    }

    fun getSetting(key: String): LiveData<Setting> = settingsDao.getSetting(key)
    fun insertSetting(setting: Setting) = viewModelScope.launch(Dispatchers.IO) { settingsDao.insertSettings(setting) }
}