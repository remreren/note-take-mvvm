package com.bukonudakonusalim.takenotes.ui.editnotebook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.app.TaskStackBuilder
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bukonudakonusalim.takenotes.R
import com.bukonudakonusalim.takenotes.data.ViewModelProviderFactory
import com.bukonudakonusalim.takenotes.data.model.Notebook
import com.bukonudakonusalim.takenotes.data.viewmodel.NotebooksViewModel
import com.bukonudakonusalim.takenotes.databinding.ActivityEditNotebookBinding
import com.bukonudakonusalim.takenotes.ui.colorselection.DialogColorSelect
import com.bukonudakonusalim.takenotes.ui.main.MainActivity
import com.bukonudakonusalim.takenotes.utils.ColorUtils
import logme.log.Logme

class EditNotebookActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityEditNotebookBinding
    private var mColor = "blue"
    private val mNotebookIndex: Long by lazy { intent.getLongExtra("notebook_id", 0) }
    private lateinit var mNotebooksViewModel: NotebooksViewModel
    private lateinit var mNotebook: Notebook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNotebooksViewModel = ViewModelProvider(this, ViewModelProviderFactory(this.application, mNotebookIndex)).get(NotebooksViewModel::class.java)
        Logme.wtf("$mNotebookIndex")
        mNotebooksViewModel.notebooksDao.getNotebookById(mNotebookIndex).observe(this, {notebook->
            mNotebook = notebook
            Logme.wtf(notebook.toString())
        })
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_notebook)
        setSupportActionBar(mBinding.toolbarEditNotebook)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mBinding.btnEditNotebook.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_notebook_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val upIntent = NavUtils.getParentActivityIntent(this)
                if (NavUtils.shouldUpRecreateTask(this, upIntent!!)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities()
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    NavUtils.navigateUpTo(this, upIntent)
                }
                return true;
            }

            R.id.menu_select_color -> {
                DialogColorSelect.make(this, this::setColoring)
                return true
            }

            R.id.menu_delete_notebook -> {
                mNotebooksViewModel.deleteNotebook(mNotebook)
                val up = Intent(this@EditNotebookActivity, MainActivity::class.java)
                if (NavUtils.shouldUpRecreateTask(this, up)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(up)
                            .startActivities()
                } else {
                    up.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    NavUtils.navigateUpTo(this, up)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setColoring(color: String) {
        mColor = color
        mBinding.toolbarEditNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0])
        mBinding.appbarEditNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0])
        mBinding.btnEditNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0])
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            mBinding.btnEditNotebook.id -> {
                TODO("update item")
            }
        }
    }
}