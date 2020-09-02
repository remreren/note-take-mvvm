package com.bukonudakonusalim.takenotes.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bukonudakonusalim.takenotes.R;

public class DialogColorSelect extends Dialog {

    private RecyclerView mRvColors;
    private ColorSelectionAdapter mAdapter;
    private OnColorInteraction mOnColorInteraction;

    public static Dialog make(Context context, OnColorInteraction onColorInteraction) {
        Dialog dialog = new DialogColorSelect(context, onColorInteraction);
        dialog.show();
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public DialogColorSelect(@NonNull Context context, OnColorInteraction onColorInteraction) {
        super(context);
        mOnColorInteraction = onColorInteraction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_color);

        mRvColors = findViewById(R.id.rv_colors);

        initColorSelectionRecyclerview();
    }

    private void initColorSelectionRecyclerview() {
        mAdapter = new ColorSelectionAdapter();
        setColorSelectionRecyclerview();
    }

    private void setColorSelectionRecyclerview() {
        mAdapter.setOnColorListener(pos -> {
            if (mOnColorInteraction != null)
                mOnColorInteraction.onColorSelected(mAdapter.getItemAt(pos));
        });
        mRvColors.setLayoutManager(new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false));
        mRvColors.setAdapter(mAdapter);
    }

    public interface OnColorInteraction {
        void onColorSelected(String color);
    }
}
