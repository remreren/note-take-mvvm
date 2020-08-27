package com.bukonudakonusalim.takenotes.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bukonudakonusalim.takenotes.Datas;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private NotebooksAdapter mNotebooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initNotebooksViewpager();
    }

    private void initNotebooksViewpager() {
        mNotebooksAdapter = new NotebooksAdapter();
        setNotebooksViewpager();
    }

    private void setNotebooksViewpager() {
        mNotebooksAdapter.setNotebooks(Datas.getNoteBooks());
        mBinding.vpNotebooks.setAdapter(mNotebooksAdapter);
    }
}