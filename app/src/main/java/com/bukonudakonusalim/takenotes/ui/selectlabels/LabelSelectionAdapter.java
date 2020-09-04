package com.bukonudakonusalim.takenotes.ui.selectlabels;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.base.BaseViewHolder;
import com.bukonudakonusalim.takenotes.data.model.LabelModel;
import com.bukonudakonusalim.takenotes.databinding.ItemLabelBinding;
import com.bukonudakonusalim.takenotes.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class LabelSelectionAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<LabelModel> mLabels;
    private OnLabelListener mOnLabelListener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLabelBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_label, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mLabels != null ? mLabels.size() : 0;
    }

    public void setOnLabelListener(OnLabelListener onLabelListener) {
        mOnLabelListener = onLabelListener;
    }

    public void setLabels(List<LabelModel> labels) {
        mLabels = new ArrayList<>(labels);
        notifyDataSetChanged();
    }

    public void reverseSelection(int pos) {
        mLabels.get(pos).setSelected(!mLabels.get(pos).isSelected());
        notifyItemChanged(pos);
    }

    public LabelModel getItemAt(int pos) {
        return mLabels.get(pos);
    }

    class ItemViewHolder extends BaseViewHolder implements View.OnClickListener {

        private ItemLabelBinding mBinding;

        public ItemViewHolder(ItemLabelBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void bind(int pos) {
            mBinding.setColor(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, ColorUtils.getColorTwist(mBinding.getRoot().getContext(), mLabels.get(pos).getColor())));
            mBinding.setSelected(mLabels.get(pos).isSelected());
            mBinding.setName(mLabels.get(pos).getName());
            mBinding.btnSelectColor.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnLabelListener != null) {
                if (view.getId() == mBinding.btnSelectColor.getId()) {
                    mOnLabelListener.onLabelSelected(getAdapterPosition());
                    reverseSelection(getAdapterPosition());
                }
            }
        }
    }

    public interface OnLabelListener {
        void onLabelSelected(int pos);
    }
}
