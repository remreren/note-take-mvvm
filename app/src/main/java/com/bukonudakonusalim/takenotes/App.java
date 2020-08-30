package com.bukonudakonusalim.takenotes;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import logme.log.Logme;

public class App extends Application {

    public static Logme.DatabaseTree tree;

    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);
        Logme.plant(tree = new Logme.DatabaseTree(this));
    }
}
