package com.bukonudakonusalim.takenotes.ui.notebook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bukonudakonusalim.takenotes.R
import com.bukonudakonusalim.takenotes.data.ViewModelProviderFactory
import com.bukonudakonusalim.takenotes.data.viewmodel.NotesViewModel
import com.bukonudakonusalim.takenotes.databinding.ActivityNotebookBinding
import com.bukonudakonusalim.takenotes.ui.editnotebook.EditNotebookActivity
import com.bukonudakonusalim.takenotes.ui.newnote.CreateNoteActivity
import com.bukonudakonusalim.takenotes.utils.ColorUtils

class NotebookActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var mBinding: ActivityNotebookBinding

    private val mNotebookIndex: Long by lazy { intent.getLongExtra("notebook_id", 0) }
    private val mViewModel: NotesViewModel by lazy { ViewModelProvider(this, ViewModelProviderFactory(this.application, mNotebookIndex)).get(NotesViewModel::class.java) }

    private lateinit var mNotesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notebook)
        setSupportActionBar(mBinding.notesToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initNotesRecyclerview()
        mBinding.btnAddNote.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notebooks_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setColoring(color: String) {
        mBinding.notesAppbar.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0])
        mBinding.notesToolbar.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0])
        mBinding.btnAddNote.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0])
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_edit_notebook) {
            val editNotebook = Intent(this@NotebookActivity, EditNotebookActivity::class.java)
            startActivity(editNotebook)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initNotesRecyclerview() {
        mNotesAdapter = NotesAdapter()
        setNotesRecyclerview()
    }

    private fun setNotesRecyclerview() {
        mBinding.rvNotes.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mBinding.rvNotes.adapter = mNotesAdapter
        mViewModel.allNotes.observe(this, mNotesAdapter::setNotes)
    }

    override fun onClick(view: View) {
        if (view.id == mBinding.btnAddNote.id) {
            val intent = Intent(this@NotebookActivity, CreateNoteActivity::class.java)
            intent.putExtra("notebook_id", mNotebookIndex)
            startActivity(intent)
        }
    }
}