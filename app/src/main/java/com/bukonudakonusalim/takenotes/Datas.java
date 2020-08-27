package com.bukonudakonusalim.takenotes;

import com.bukonudakonusalim.takenotes.data.model.NotebookModel;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Datas {

    public static List<NotebookModel> getNoteBooks() {
        ArrayList<NotebookModel> notebooks = new ArrayList<>();
        notebooks.add(new NotebookModel(0, "Test notebook", "Notebook description", "#FFFFFF", 1, false, new DateTime(2018, 10, 23, 12, 34), new DateTime(2018, 10, 23, 12, 34)));
        notebooks.add(new NotebookModel(1, "Test notebook 2", "Notebook description 2", "#FFFFFF", 1, false, new DateTime(2019, 10, 23, 12, 34), new DateTime(2019, 10, 23, 12, 34)));
        return notebooks;
    }
}
