package com.bukonudakonusalim.takenotes.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.bukonudakonusalim.takenotes.data.model.NotebookModel;
import com.bukonudakonusalim.takenotes.utils.ColorUtils;
import com.bukonudakonusalim.takenotes.utils.DatabaseController;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityMainBinding;
import com.bukonudakonusalim.takenotes.ui.logs.LogsActivity;
import com.bukonudakonusalim.takenotes.ui.newnotebook.CreateNotebookActivity;
import com.bukonudakonusalim.takenotes.ui.notebook.NotebookActivity;
import com.bukonudakonusalim.takenotes.utils.TimeUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;

    private NotebooksAdapter mNotebooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.btnLogs.setOnClickListener(this);
        mBinding.tvTodayDay.setText(TimeUtils.getTodayLong());
        mBinding.etNameOfPerson.setOnFocusChangeListener((view, b) -> mBinding.etNameOfPerson.setCursorVisible(b));
        mBinding.etNameOfPerson.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                mBinding.etNameOfPerson.clearFocus();
                mBinding.etNameOfPerson.setCursorVisible(false);
            }
            return false;
        });
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
                notebookScreen.putExtra("notebook_id", mNotebooksAdapter.getItemAt(pos).getId());
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
        new Thread(() -> {
            List<NotebookModel> notebooks = NotebookModel.getAllNotebooks(DatabaseController.getInstance(MainActivity.this));
            runOnUiThread(() -> {
                mNotebooksAdapter.setNotebooks(notebooks);
                mBinding.vpNotebooks.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        if (position != mNotebooksAdapter.getItemCount() - 1) {
                            mBinding.background.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, ColorUtils.getColorTwist(MainActivity.this, mNotebooksAdapter.getItemAt(position).getNotebookColor())));
                        } else {
                            mBinding.background.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, ColorUtils.getColorTwist(MainActivity.this, "light_blue")));
                        }
                    }
                });
            });
        }).start();
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