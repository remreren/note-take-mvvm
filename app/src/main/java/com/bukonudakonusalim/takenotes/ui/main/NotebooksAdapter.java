package com.bukonudakonusalim.takenotes.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.model.NotebookModel;
import com.bukonudakonusalim.takenotes.databinding.NotebookItemBinding;
import com.bukonudakonusalim.takenotes.utils.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NotebooksAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<NotebookModel> mNotebooks;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotebookItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.notebook_item, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNotebooks != null ? mNotebooks.size() : 0;
    }

    public void setNotebooks(List<NotebookModel> notebooks) {
        mNotebooks = new ArrayList<>(notebooks);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends BaseViewHolder {

        protected NotebookItemBinding mBinding;

        public ItemViewHolder(NotebookItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void bind(int pos) {
            mBinding.setNotebook(mNotebooks.get(pos));
        }
    }
}
