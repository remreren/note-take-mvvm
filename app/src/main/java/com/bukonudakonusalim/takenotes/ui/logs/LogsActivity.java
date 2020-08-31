package com.bukonudakonusalim.takenotes.ui.logs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bukonudakonusalim.takenotes.App;
import com.bukonudakonusalim.takenotes.R;
import com.bukonudakonusalim.takenotes.databinding.ActivityLogsBinding;
import com.bukonudakonusalim.takenotes.ui.HoverLogService;

import java.util.List;

import io.mattcarroll.hover.HoverView;
import io.mattcarroll.hover.window.WindowViewController;
import logme.log.Hover;
import logme.log.LogModel;

public class LogsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLogsBinding mBinding;
    private LogsAdapter mLogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_logs);

        mBinding.openHover.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if (view.getId() == mBinding.openHover.getId()) {
            Hover.openLogme(this);
//            HoverView hover = HoverView.createForWindow(this, new WindowViewController((WindowManager) getSystemService(Context.WINDOW_SERVICE)));
//            hover.setOnExitListener(() -> {
//
//            });
//            hover.addToWindow();
//            hover.setMenu(new HoverLogService(this));
//            hover.collapse();
        }
    }
}