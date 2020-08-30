package com.bukonudakonusalim.takenotes.ui.logs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bukonudakonusalim.takenotes.App;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityLogsBinding;

import java.util.List;

import logme.log.LogModel;

public class LogsActivity extends AppCompatActivity {

    private ActivityLogsBinding mBinding;
    private LogsAdapter mLogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_logs);

        initLogsRecyclerview();
    }

    private void initLogsRecyclerview() {
        mLogsAdapter = new LogsAdapter();
        setLogsRecyclerview();
    }

    private void setLogsRecyclerview() {
        mBinding.rvLogs.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mBinding.rvLogs.setAdapter(mLogsAdapter);
    }

    @Override
    protected void onResume() {
        new Thread(() -> {
            List<LogModel> logs = App.tree.getDatabase().logDao().getAllLogs();
            runOnUiThread(() -> mLogsAdapter.setLogs(logs));
        }).start();
        super.onResume();
    }
}