package com.bukonudakonusalim.takenotes.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.model.NotebookModel;
import com.bukonudakonusalim.takenotes.databinding.NotebookAddItemBinding;
import com.bukonudakonusalim.takenotes.databinding.NotebookItemBinding;
import com.bukonudakonusalim.takenotes.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NotebooksAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<NotebookModel> mNotebooks;
    private OnNotebooksClickListener mOnNotebooksClickListener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.notebook_item) {
            NotebookItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.notebook_item, parent, false);
            return new NotebookItemViewHolder(binding);
        } else if (viewType == R.layout.notebook_add_item) {
            NotebookAddItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.notebook_add_item, parent, false);
            return new AddNotebookViewHolder(binding);
        } else throw new IllegalStateException("illegal view");
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return (mNotebooks != null ? mNotebooks.size() : 0) + (hasExtraRow() ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && hasExtraRow()) return R.layout.notebook_add_item;
        else return R.layout.notebook_item;
    }

    private boolean hasExtraRow() {
        return mNotebooks == null || mNotebooks.size() < 10;
    }

    public void setNotebooks(List<NotebookModel> notebooks) {
        mNotebooks = new ArrayList<>(notebooks);
        notifyDataSetChanged();
    }

    public void setOnNotebooksClickListener(OnNotebooksClickListener onNotebooksClickListener) {
        mOnNotebooksClickListener = onNotebooksClickListener;
    }

    public NotebookModel getItemAt(int pos) {
        return mNotebooks.get(pos);
    }

    class NotebookItemViewHolder extends BaseViewHolder implements View.OnClickListener {

        protected NotebookItemBinding mBinding;

        public NotebookItemViewHolder(NotebookItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void bind(int pos) {
            mBinding.setNotebook(mNotebooks.get(pos));
            mBinding.btnOpenNotebook.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnNotebooksClickListener != null) {
                if (view.getId() == mBinding.btnOpenNotebook.getId())
                    mOnNotebooksClickListener.onNotebookClick(getAdapterPosition());
            }
        }
    }

    class AddNotebookViewHolder extends BaseViewHolder implements View.OnClickListener {

        protected NotebookAddItemBinding mBinding;

        public AddNotebookViewHolder(NotebookAddItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void bind(int pos) {
            mBinding.btnCreateNotebook.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnNotebooksClickListener != null) {
                if (view.getId() == mBinding.btnCreateNotebook.getId())
                    mOnNotebooksClickListener.onCreateClick();
            }
        }
    }

    public interface OnNotebooksClickListener {
        void onNotebookClick(int pos);

        void onCreateClick();
    }
}
