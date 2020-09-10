package com.bukonudakonusalim.takenotes.ui.newnotebook

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bukonudakonusalim.takenotes.R
import com.bukonudakonusalim.takenotes.data.ViewModelProviderFactory
import com.bukonudakonusalim.takenotes.data.model.Notebook
import com.bukonudakonusalim.takenotes.data.viewmodel.NotebooksViewModel
import com.bukonudakonusalim.takenotes.databinding.ActivityCreateNotebookBinding

class CreateNotebookActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityCreateNotebookBinding
    private val mNotebooksViewModel: NotebooksViewModel by lazy { ViewModelProvider(this, ViewModelProviderFactory(this.application, null)).get(NotebooksViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_notebook)
        setSupportActionBar(mBinding.toolbarNewNotebook)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mBinding.btnCreateNotebook.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == mBinding.btnCreateNotebook.id) {
            mNotebooksViewModel.insertNotebook(Notebook(mBinding.etNotebookTitle.text.toString().trim(), mBinding.etNotebookContent.text.toString().trim(), 0)).invokeOnCompletion { finish() }
        }
    }
}