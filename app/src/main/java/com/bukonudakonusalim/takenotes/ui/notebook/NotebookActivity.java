package com.bukonudakonusalim.takenotes.ui.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bukonudakonusalim.takenotes.Datas;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityNotebookBinding;
import com.bukonudakonusalim.takenotes.ui.newnote.CreateNoteActivity;
import com.bukonudakonusalim.takenotes.ui.newnotebook.CreateNotebookActivity;

public class NotebookActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityNotebookBinding mBinding;

    private NotesAdapter mNotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notebook);
        setSupportActionBar(mBinding.notesToolbar);

        initNotesRecyclerview();

        mBinding.btnAddNote.setOnClickListener(this);
    }

    private void initNotesRecyclerview() {
        mNotesAdapter = new NotesAdapter();
        setNotesRecyclerview();
    }

    private void setNotesRecyclerview() {
        mBinding.rvNotes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mBinding.rvNotes.setAdapter(mNotesAdapter);
    }

    @Override
    protected void onResume() {
        mNotesAdapter.setNotes(Datas.getNotes());
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.btnAddNote.getId()) {
//            mNotesAdapter.addNotes(Datas.getNotes());
            Intent intent = new Intent(NotebookActivity.this, CreateNoteActivity.class);
            startActivity(intent);
        }
    }
}