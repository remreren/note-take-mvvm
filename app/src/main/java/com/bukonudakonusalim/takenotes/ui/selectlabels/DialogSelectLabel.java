package com.bukonudakonusalim.takenotes.ui.selectlabels;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.DataHolder;
import com.bukonudakonusalim.takenotes.data.model.LabelModel;

public class DialogSelectLabel extends Dialog {

    private RecyclerView mRvColors;
    private LabelSelectionAdapter mAdapter;
    private OnLabelInteraction mOnLabelInteraction;

    public static Dialog make(Context context, OnLabelInteraction onLabelInteraction) {
        Dialog dialog = new DialogSelectLabel(context, onLabelInteraction);
        dialog.show();
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public DialogSelectLabel(@NonNull Context context, OnLabelInteraction onLabelInteraction) {
        super(context);
        mOnLabelInteraction = onLabelInteraction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_color);

        mRvColors = findViewById(R.id.rv_colors);

        initColorSelectionRecyclerview();
    }

    private void initColorSelectionRecyclerview() {
        mAdapter = new LabelSelectionAdapter();
        setColorSelectionRecyclerview();
    }

    private void setColorSelectionRecyclerview() {
        mAdapter.setOnLabelListener(pos -> {
            if (mOnLabelInteraction != null)
                mOnLabelInteraction.onLabelSelected(mAdapter.getItemAt(pos));
        });
        mAdapter.setLabels(DataHolder.getInstance().getNotebooks().get(DataHolder.getInstance().getSelectedIndex()).getLabels());
        mRvColors.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRvColors.setAdapter(mAdapter);
    }

    public interface OnLabelInteraction {
        void onLabelSelected(LabelModel label);
    }
}
