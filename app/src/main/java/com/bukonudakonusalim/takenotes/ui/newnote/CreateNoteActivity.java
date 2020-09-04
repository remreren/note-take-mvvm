package com.bukonudakonusalim.takenotes.ui.newnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.bukonudakonusalim.takenotes.data.DataHolder;
import com.bukonudakonusalim.takenotes.utils.ColorUtils;
import com.bukonudakonusalim.takenotes.utils.DatabaseController;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.model.NoteModel;
import com.bukonudakonusalim.takenotes.databinding.ActivityCreateNoteBinding;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCreateNoteBinding mBinding;
    private int mNotebookIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_note);
        setSupportActionBar(mBinding.toolbarNewNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNotebookIndex = DataHolder.getInstance().getSelectedIndex();
        setColoring(DataHolder.getInstance().getNotebooks().get(mNotebookIndex).getNotebookColor());

        mBinding.btnCreateNote.setOnClickListener(this);
    }

    private void setColoring(String color) {
        mBinding.toolbarNewNote.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
        mBinding.appbarNewNote.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
        mBinding.btnCreateNote.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.btnCreateNote.getId()) {
            new NoteModel(mBinding.etNoteTitle.getText().toString(), mBinding.etNoteContent.getText().toString(), null).save(DatabaseController.getInstance(this), mNotebookIndex);
            this.finish();
        }
    }
}