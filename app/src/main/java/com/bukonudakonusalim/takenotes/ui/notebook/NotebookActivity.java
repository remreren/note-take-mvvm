package com.bukonudakonusalim.takenotes.ui.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bukonudakonusalim.takenotes.data.model.NoteModel;
import com.bukonudakonusalim.takenotes.utils.DatabaseController;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityNotebookBinding;
import com.bukonudakonusalim.takenotes.ui.newnote.CreateNoteActivity;

import java.util.List;

public class NotebookActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityNotebookBinding mBinding;

    private NotesAdapter mNotesAdapter;
    private int mNotebookIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notebook);
        setSupportActionBar(mBinding.notesToolbar);

        mNotebookIndex = getIntent().getIntExtra("notebook_id", -1);

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
        new Thread(() -> {
            List<NoteModel> notes = NoteModel.getAllNotes(DatabaseController.getInstance(this), mNotebookIndex);
            runOnUiThread(() -> mNotesAdapter.setNotes(notes));
        }).start();
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.btnAddNote.getId()) {
            Intent intent = new Intent(NotebookActivity.this, CreateNoteActivity.class);
            intent.putExtra("notebook_id", mNotebookIndex);
            startActivity(intent);
        }
    }
}