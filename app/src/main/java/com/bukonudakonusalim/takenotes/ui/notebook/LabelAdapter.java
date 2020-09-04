package com.bukonudakonusalim.takenotes.ui.notebook;

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
import com.bukonudakonusalim.takenotes.databinding.ItemLabelNoteBinding;

import java.util.List;

public class LabelAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<LabelModel> mLabels;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLabelNoteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_label_note, parent, false);
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

    public void setLabels(List<LabelModel> labels) {
        mLabels = labels;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends BaseViewHolder {

        private ItemLabelNoteBinding mBinding;

        public ItemViewHolder(ItemLabelNoteBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void bind(int pos) {
            mBinding.setLabel(mLabels.get(pos));
        }
    }
}
