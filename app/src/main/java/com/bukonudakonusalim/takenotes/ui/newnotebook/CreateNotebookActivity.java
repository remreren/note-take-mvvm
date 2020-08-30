package com.bukonudakonusalim.takenotes.ui.newnotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.bukonudakonusalim.takenotes.utils.Datas;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.model.NotebookModel;
import com.bukonudakonusalim.takenotes.databinding.ActivityCreateNotebookBinding;

import org.joda.time.DateTime;

public class CreateNotebookActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCreateNotebookBinding mBinding;

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
            Datas.getNoteBooks().add(new NotebookModel(2, mBinding.etNotebookTitle.getText().toString(), mBinding.etNotebookContent.getText().toString(), "#FFFFFF", 1, false, DateTime.now(), DateTime.now()));
            this.finish();
        }
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
        }
        return super.onOptionsItemSelected(item);
    }
}