package com.bukonudakonusalim.takenotes.ui.logs;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.LogItemBinding;
import com.bukonudakonusalim.takenotes.utils.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import advancelogger.log.LogModel;

public class LogsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<LogModel> mLogs;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogItemBinding logItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.log_item, parent, false);
        return new LogItemViewHolder(logItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mLogs != null ? mLogs.size() : 0;
    }

    public void setLogs(List<LogModel> logs) {
        mLogs = new ArrayList<>(logs);
        notifyDataSetChanged();
    }

    class LogItemViewHolder extends BaseViewHolder {

        private LogItemBinding mBinding;

        public LogItemViewHolder(LogItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void bind(int pos) {
            mBinding.setLog(mLogs.get(pos));
        }
    }
}
