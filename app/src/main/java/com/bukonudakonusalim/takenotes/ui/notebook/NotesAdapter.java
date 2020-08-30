package com.bukonudakonusalim.takenotes.ui.notebook;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.data.model.NoteModel;
import com.bukonudakonusalim.takenotes.databinding.NoteItemBinding;
import com.bukonudakonusalim.takenotes.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<NoteModel> mNotes;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteItemBinding noteItem = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.note_item, parent, false);
        return new NoteItemViewHolder(noteItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNotes != null ? mNotes.size() : 0;
    }

    public void setNotes(List<NoteModel> notes) {
        mNotes = new ArrayList<>(notes);
        notifyDataSetChanged();
    }

    public void addNotes(List<NoteModel> notes) {
        if (mNotes == null) mNotes = new ArrayList<>();
        mNotes.addAll(notes);
        notifyItemRangeInserted(mNotes.size() - notes.size(), mNotes.size() - 1);
    }

    class NoteItemViewHolder extends BaseViewHolder {

        private NoteItemBinding mBinding;

        public NoteItemViewHolder(NoteItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void bind(int pos) {
            mBinding.setNote(mNotes.get(pos));
        }
    }
}
