package com.bukonudakonusalim.takenotes.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bukonudakonusalim.takenotes.utils.Datas;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityMainBinding;
import com.bukonudakonusalim.takenotes.ui.logs.LogsActivity;
import com.bukonudakonusalim.takenotes.ui.newnotebook.CreateNotebookActivity;
import com.bukonudakonusalim.takenotes.ui.notebook.NotebookActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;

    private NotebooksAdapter mNotebooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.btnLogs.setOnClickListener(this);
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

        mBinding.vpNotebooks.setAdapter(mNotebooksAdapter);
        mBinding.vpNotebooks.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mBinding.vpNotebooks.setOffscreenPageLimit(3);
        setPageTransformer();
    }

    private void setPageTransformer() {
        float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        float selectedHeight = getResources().getDimensionPixelOffset(R.dimen.selectedHeight);
        float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        mBinding.vpNotebooks.setPageTransformer((page, position) -> {
            float offset = position * -(2 * pageOffset + pageMargin);
            float height = Math.abs(position) * selectedHeight;
//            float scale = (float) (1f - (0.1 * Math.pow(position, 2)));
            if (mBinding.vpNotebooks.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(mBinding.vpNotebooks) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.setTranslationX(-offset);
                } else {
                    page.setTranslationX(offset);
                }
                page.setTranslationY(height);
//                page.setScaleY(scale);
            } else {
                page.setTranslationY(offset);
            }
        });
    }

    @Override
    protected void onResume() {
        mNotebooksAdapter.setNotebooks(Datas.getNoteBooks());
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.btnLogs.getId()) {
            Intent logs = new Intent(MainActivity.this, LogsActivity.class);
            startActivity(logs);
        }
    }
}