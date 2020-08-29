package com.bukonudakonusalim.takenotes.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.bukonudakonusalim.takenotes.Datas;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityMainBinding;
import com.bukonudakonusalim.takenotes.ui.newnotebook.CreateNotebookActivity;
import com.bukonudakonusalim.takenotes.ui.notebook.NotebookActivity;

import advancelogger.log.AdvanceLogger;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private NotebooksAdapter mNotebooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        AdvanceLogger.wtf("restarted");

        initNotebooksViewpager();
    }

    private void initNotebooksViewpager() {
        mNotebooksAdapter = new NotebooksAdapter();
        setNotebooksViewpager();
    }

    private void setNotebooksViewpager() {
        mNotebooksAdapter.setOnNotebooksClickListener(new NotebooksAdapter.OnNotebooksClickListener() {
            @Override
            public void onNotebookClick(int pos) {
                Intent notebookScreen = new Intent(MainActivity.this, NotebookActivity.class);
                startActivity(notebookScreen);
            }

            @Override
            public void onCreateClick() {
                Intent createNotebook = new Intent(MainActivity.this, CreateNotebookActivity.class);
                startActivity(createNotebook);
            }
        });

        float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        mBinding.vpNotebooks.setAdapter(mNotebooksAdapter);
        mBinding.vpNotebooks.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mBinding.vpNotebooks.setOffscreenPageLimit(3);
        mBinding.vpNotebooks.setPageTransformer((page, position) -> {
            float offset = position * -(2 * pageOffset + pageMargin);
//            float scale = (float) (1f - (0.1 * Math.pow(position, 2)));
            if (mBinding.vpNotebooks.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(mBinding.vpNotebooks) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.setTranslationX(-offset);
                } else {
                    page.setTranslationX(offset);
                }
//                page.setScaleY(scale);
            } else {
                page.setTranslationY(offset);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNotebooksAdapter.setNotebooks(Datas.getNoteBooks());
    }
}