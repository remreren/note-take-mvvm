package com.bukonudakonusalim.takenotes.ui.newnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.bukonudakonusalim.takenotes.Datas;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.model.NoteModel;
import com.bukonudakonusalim.takenotes.databinding.ActivityCreateNoteBinding;

import org.joda.time.DateTime;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCreateNoteBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_note);
        setSupportActionBar(mBinding.toolbarNewNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBinding.btnCreateNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.btnCreateNote.getId()) {
            Datas.getNotes().add(new NoteModel(6, mBinding.etNoteTitle.getText().toString(), mBinding.etNoteContent.getText().toString(), null, DateTime.now(), DateTime.now(), false));
            this.finish();
        }
    }
}