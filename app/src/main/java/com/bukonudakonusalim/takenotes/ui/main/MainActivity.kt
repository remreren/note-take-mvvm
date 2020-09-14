package com.bukonudakonusalim.takenotes.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bukonudakonusalim.takenotes.R
import com.bukonudakonusalim.takenotes.data.DataHolder
import com.bukonudakonusalim.takenotes.data.ViewModelProviderFactory
import com.bukonudakonusalim.takenotes.data.model.Setting
import com.bukonudakonusalim.takenotes.data.viewmodel.MainViewModel
import com.bukonudakonusalim.takenotes.data.viewmodel.NotebooksViewModel
import com.bukonudakonusalim.takenotes.databinding.ActivityMainBinding
import com.bukonudakonusalim.takenotes.ui.main.NotebooksAdapter.OnNotebooksClickListener
import com.bukonudakonusalim.takenotes.ui.newnotebook.CreateNotebookActivity
import com.bukonudakonusalim.takenotes.ui.notebook.NotebookActivity
import com.bukonudakonusalim.takenotes.utils.TimeUtils
import logme.log.Logme


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private val mMainViewModel: MainViewModel by lazy { ViewModelProvider(this, ViewModelProviderFactory(this.application, null)).get(MainViewModel::class.java) }
    private val mNotebooksViewModel: NotebooksViewModel by lazy { ViewModelProvider(this, ViewModelProviderFactory(this.application, null)).get(NotebooksViewModel::class.java) }

    private lateinit var mNotebooksAdapter: NotebooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logme.wtf("Main activity started...")
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.tvTodayDay.text = TimeUtils.getTodayLong()

        mMainViewModel.getSetting("user_name").observe(this, { setting ->
            val username: String? = setting?.value
            mBinding.etNameOfPerson.apply {
                mBinding.etNameOfPerson.setText(username ?: "!")
                setOnFocusChangeListener { _, b -> mBinding.etNameOfPerson.isCursorVisible = b }
                setOnEditorActionListener { _, i, _ ->
                    if (i === EditorInfo.IME_ACTION_DONE) {
                        mMainViewModel.insertSetting(Setting("user_name", mBinding.etNameOfPerson.text.toString().trim()))
                        mBinding.etNameOfPerson.apply {
                            setText(mBinding.etNameOfPerson.text.toString().trim())
                            clearFocus()
                            isCursorVisible = false
                        }
                    }
                    false
                }
            }
        })

        initNotebooksViewpager()
    }

    private fun initNotebooksViewpager() {
        mNotebooksAdapter = NotebooksAdapter()
        setNotebooksViewpager()
    }

    private fun setNotebooksViewpager() {
        mNotebooksViewModel.allNotebooks.observe(this, { notebooks ->
            mNotebooksAdapter.setNotebooks(notebooks)
        })
        mNotebooksAdapter.setOnNotebooksClickListener(object : OnNotebooksClickListener {
            override fun onNotebookClick(pos: Int) {
                val notebookScreen = Intent(this@MainActivity, NotebookActivity::class.java)
                notebookScreen.putExtra("notebook_id", mNotebooksAdapter.getItemAt(pos).id)
                startActivity(notebookScreen)
            }

            override fun onCreateClick() {
                val createNotebook = Intent(this@MainActivity, CreateNotebookActivity::class.java)
                startActivity(createNotebook)
            }
        })
        mBinding.vpNotebooks.adapter = mNotebooksAdapter
        mBinding.vpNotebooks.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mBinding.vpNotebooks.offscreenPageLimit = 3
        setPageTransformer()
    }

    private fun setPageTransformer() {
        val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
        val selectedHeight = resources.getDimensionPixelOffset(R.dimen.selectedHeight).toFloat()
        val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()
        mBinding.vpNotebooks.setPageTransformer { page, position ->
            val offset = position * -(2 * pageOffset + pageMargin)
            val height = Math.abs(position) * selectedHeight
            //            float scale = (float) (1f - (0.1 * Math.pow(position, 2)));
            if (mBinding.vpNotebooks.orientation === ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(mBinding.vpNotebooks) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
                page.translationY = height
                //                page.setScaleY(scale);
            } else {
                page.translationY = offset
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            //
        }
    }
}