package com.bukonudakonusalim.takenotes;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import advancelogger.log.AdvanceLogger;

public class App extends Application {

    public static AdvanceLogger.DatabaseTree tree;

    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);
        AdvanceLogger.plant(tree = new AdvanceLogger.DatabaseTree(this));
    }
}
