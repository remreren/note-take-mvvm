package com.bukonudakonusalim.takenotes.ui.editnotebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.databinding.DataBindingUtil;

import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.DataHolder;
import com.bukonudakonusalim.takenotes.data.model.NotebookModel;
import com.bukonudakonusalim.takenotes.databinding.ActivityEditNotebookBinding;
import com.bukonudakonusalim.takenotes.ui.colorselection.DialogColorSelect;
import com.bukonudakonusalim.takenotes.ui.main.MainActivity;
import com.bukonudakonusalim.takenotes.utils.ColorUtils;
import com.bukonudakonusalim.takenotes.utils.DatabaseController;

public class EditNotebookActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditNotebookBinding mBinding;
    private String mColor = "blue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_notebook);
        setSupportActionBar(mBinding.toolbarEditNotebook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setColoring(mColor);

        mBinding.btnEditNotebook.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.btnEditNotebook.getId()) {
            new Thread(() -> {
                NotebookModel notebookModel = DataHolder.getInstance().getNotebooks().get(DataHolder.getInstance().getSelectedIndex());
                notebookModel.setNotebookName(mBinding.etNotebookTitle.getText().toString().trim());
                notebookModel.setNotebookDescription(mBinding.etNotebookContent.getText().toString().trim());
                notebookModel.setNotebookColor(mColor);
                notebookModel.update(DatabaseController.getInstance(getApplicationContext()), getApplicationContext());
                this.finish();
            }).start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_notebook_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;

            case R.id.menu_select_color:
                DialogColorSelect.make(this, this::setColoring);
                return true;

            case R.id.menu_delete_notebook:
                new Thread(() -> {
                    DataHolder.getInstance().getNotebooks().get(DataHolder.getInstance().getSelectedIndex()).delete(DatabaseController.getInstance(getApplicationContext()));
                    Intent up = new Intent(EditNotebookActivity.this, MainActivity.class);
                    if (NavUtils.shouldUpRecreateTask(this, up)) {
                        TaskStackBuilder.create(this)
                                .addNextIntentWithParentStack(up)
                                .startActivities();
                    } else {
                        up.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        NavUtils.navigateUpTo(this, up);
                    }
                }).start();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setColoring(String color) {
        mColor = color;
        mBinding.toolbarEditNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
        mBinding.appbarEditNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
        mBinding.btnEditNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
    }
}