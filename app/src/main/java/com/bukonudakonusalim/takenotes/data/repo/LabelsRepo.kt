package com.bukonudakonusalim.takenotes.data.repo

import androidx.lifecycle.LiveData
import com.bukonudakonusalim.takenotes.data.local.LabelsDao
import com.bukonudakonusalim.takenotes.data.model.Label

class LabelsRepo(private val labelsDao: LabelsDao) {
    val labels: LiveData<List<Label>> = labelsDao.getLabels()

    suspend fun insert(label: Label) {
        labelsDao.insertLabels(label)
    }
}