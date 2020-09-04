package com.bukonudakonusalim.takenotes.ui.newnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bukonudakonusalim.takenotes.data.DataHolder;
import com.bukonudakonusalim.takenotes.data.model.LabelModel;
import com.bukonudakonusalim.takenotes.ui.selectlabels.DialogSelectLabel;
import com.bukonudakonusalim.takenotes.utils.ColorUtils;
import com.bukonudakonusalim.takenotes.utils.DatabaseController;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.model.NoteModel;
import com.bukonudakonusalim.takenotes.databinding.ActivityCreateNoteBinding;

import java.util.ArrayList;
import java.util.List;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCreateNoteBinding mBinding;
    private int mNotebookIndex;
    private List<LabelModel> mSelectedLabels = new ArrayList<>();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_select_labels) {
            Dialog dialog = DialogSelectLabel.make(this, labelModel -> {
                if (labelModel.isSelected()) mSelectedLabels.remove(labelModel);
                else mSelectedLabels.add(labelModel);
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.btnCreateNote.getId()) {
            new NoteModel(mBinding.etNoteTitle.getText().toString(), mBinding.etNoteContent.getText().toString(), null).save(DatabaseController.getInstance(this), DataHolder.getInstance().getNotebooks().get(mNotebookIndex).getId());
            this.finish();
        }
    }
}