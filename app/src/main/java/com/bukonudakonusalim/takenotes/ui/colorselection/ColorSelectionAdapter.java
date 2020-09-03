package com.bukonudakonusalim.takenotes.ui.colorselection;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.base.BaseViewHolder;
import com.bukonudakonusalim.takenotes.databinding.ItemColorBinding;
import com.bukonudakonusalim.takenotes.utils.ColorUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorSelectionAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<String> mColors = new ArrayList<>(Arrays.asList("purple", "deep_purple", "red", "indigo", "blue", "light_blue", "teal", "lime", "amber", "orange", "deep_orange"));
    private OnColorListener mOnColorListener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemColorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_color, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mColors != null ? mColors.size() : 0;
    }

    public void setOnColorListener(OnColorListener onColorListener) {
        mOnColorListener = onColorListener;
    }

    public String getItemAt(int pos) {
        return mColors.get(pos);
    }

    class ItemViewHolder extends BaseViewHolder implements View.OnClickListener {

        private ItemColorBinding mBinding;

        public ItemViewHolder(ItemColorBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void bind(int pos) {
            mBinding.setColor(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, ColorUtils.getColorTwist(mBinding.getRoot().getContext(), mColors.get(pos))));
            mBinding.btnSelectColor.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnColorListener != null) {
                if (view.getId() == mBinding.btnSelectColor.getId()) {
                    mOnColorListener.onColorSelected(getAdapterPosition());
                }
            }
        }
    }

    public interface OnColorListener {
        void onColorSelected(int pos);
    }
}
