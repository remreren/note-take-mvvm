package com.bukonudakonusalim.takenotes.ui.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.bukonudakonusalim.takenotes.data.DataHolder;
import com.bukonudakonusalim.takenotes.data.model.NoteModel;
import com.bukonudakonusalim.takenotes.utils.ColorUtils;
import com.bukonudakonusalim.takenotes.utils.DatabaseController;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityNotebookBinding;
import com.bukonudakonusalim.takenotes.ui.newnote.CreateNoteActivity;

import java.util.List;

import logme.log.Logme;

public class NotebookActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityNotebookBinding mBinding;

    private NotesAdapter mNotesAdapter;
    private int mNotebookIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notebook);
        setSupportActionBar(mBinding.notesToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNotebookIndex = DataHolder.getInstance().getSelectedIndex();
        setColoring(DataHolder.getInstance().getNotebooks().get(mNotebookIndex).getNotebookColor());
        mBinding.notesToolbar.setTitle(DataHolder.getInstance().getNotebooks().get(mNotebookIndex).getNotebookName());

        initNotesRecyclerview();

        mBinding.btnAddNote.setOnClickListener(this);;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notebooks_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initNotesRecyclerview() {
        mNotesAdapter = new NotesAdapter();
        setNotesRecyclerview();
    }

    private void setNotesRecyclerview() {
        mBinding.rvNotes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mBinding.rvNotes.setAdapter(mNotesAdapter);
    }

    private void setColoring(String color) {
        mBinding.notesAppbar.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
        mBinding.notesToolbar.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
        mBinding.btnAddNote.setBackgroundColor(ColorUtils.getColorTwist(this, color)[0]);
    }

    @Override
    protected void onResume() {
        new Thread(() -> {
            List<NoteModel> notes = NoteModel.getAllNotes(DatabaseController.getInstance(this), DataHolder.getInstance().getNotebooks().get(mNotebookIndex).getId());
            DataHolder.getInstance().getNotebooks().get(mNotebookIndex).setNotes(notes);
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