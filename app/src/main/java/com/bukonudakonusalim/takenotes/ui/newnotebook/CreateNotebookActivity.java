package com.bukonudakonusalim.takenotes.ui.newnotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bukonudakonusalim.takenotes.utils.ColorUtils;
import com.bukonudakonusalim.takenotes.utils.DatabaseController;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.model.NotebookModel;
import com.bukonudakonusalim.takenotes.databinding.ActivityCreateNotebookBinding;
import com.bukonudakonusalim.takenotes.utils.DialogColorSelect;

public class CreateNotebookActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCreateNotebookBinding mBinding;
    private String mColor = "light_blue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_notebook);
        setSupportActionBar(mBinding.toolbarNewNotebook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBinding.btnCreateNotebook.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.btnCreateNotebook.getId()) {
            new NotebookModel(mBinding.etNotebookTitle.getText().toString(), mBinding.etNotebookContent.getText().toString(), mColor, 1).save(DatabaseController.getInstance(this));
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_notebook_menu, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void setColoring(String color) {
        mColor = color;
        mBinding.toolbarNewNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
        mBinding.appbarNewNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[1]);
        mBinding.btnCreateNotebook.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
    }
}