package com.bukonudakonusalim.takenotes.data;

import com.bukonudakonusalim.takenotes.data.model.NotebookModel;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class DataHolder {

    private List<NotebookModel> mNotebooks;
    private int mSelectedIndex = 0;
    private static DataHolder ourInstance = new DataHolder();

    public static DataHolder getInstance() {
        if(ourInstance == null) ourInstance = new DataHolder();
        return ourInstance;
    }

    private DataHolder() {

    }

    public List<NotebookModel> getNotebooks() {
        if (mNotebooks == null) mNotebooks = new ArrayList<>();
        return mNotebooks;
    }

    public void setNotebooks(List<NotebookModel> notebooks) {
        mNotebooks = notebooks;
    }

    public int getSelectedIndex() {
        return mSelectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        mSelectedIndex = selectedIndex;
    }
}
