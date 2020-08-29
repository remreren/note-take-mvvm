package com.bukonudakonusalim.takenotes;

import com.bukonudakonusalim.takenotes.data.model.NoteModel;
import com.bukonudakonusalim.takenotes.data.model.NotebookModel;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datas {

    private static List<NotebookModel> mNotebooks = new ArrayList<>(Arrays.asList(new NotebookModel(0, "Test notebook", "Notebook description", "#FFFFFF", 1, false, new DateTime(2018, 10, 23, 12, 34), new DateTime(2018, 10, 23, 12, 34)), new NotebookModel(1, "Test notebook 2", "Notebook description 2", "#FFFFFF", 1, false, new DateTime(2019, 10, 23, 12, 34), new DateTime(2019, 10, 23, 12, 34))));
    private static List<NoteModel> mNotes = new ArrayList<>(Arrays.asList(
            new NoteModel(0, "title 1", "content1", Arrays.asList(1, 2), new DateTime(2018, 10, 23, 12, 34), new DateTime(2018, 10, 23, 12, 34), false),
            new NoteModel(0, "title 2", "content2", Arrays.asList(1, 2), new DateTime(2018, 10, 23, 12, 34), new DateTime(2018, 10, 23, 12, 34), false),
            new NoteModel(0, "title 3", "content3", Arrays.asList(1, 2), new DateTime(2019, 10, 23, 12, 34), new DateTime(2019, 10, 23, 12, 34), false),
            new NoteModel(0, "title 4", "content4", Arrays.asList(1, 2), new DateTime(2019, 10, 23, 12, 34), new DateTime(2019, 10, 23, 12, 34), false),
            new NoteModel(0, "title 5", "content5", Arrays.asList(1, 2), new DateTime(2019, 10, 23, 12, 34), new DateTime(2019, 10, 23, 12, 34), false)));

    public static List<NotebookModel> getNoteBooks() {
        return mNotebooks;
    }

    public static List<NoteModel> getNotes() {
        return mNotes;
    }
}
