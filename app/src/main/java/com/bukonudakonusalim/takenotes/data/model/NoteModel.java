package com.bukonudakonusalim.takenotes.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bukonudakonusalim.takenotes.utils.DatabaseController;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.bukonudakonusalim.takenotes.utils.DatabaseController.*;

public class NoteModel {

    private int id;
    private String title;
    private String content;
    private List<Integer> labels;
    private DateTime createdAt;
    private DateTime updatedAt;
    private boolean isDeleted;

    public NoteModel(int id, String title, String content, List<Integer> labels, DateTime createdAt, DateTime updatedAt, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.labels = labels;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public NoteModel(String title, String content, List<Integer> labels) {
        this.title = title;
        this.content = content;
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void save(DatabaseController controller, long notebookId) {
        SQLiteDatabase db = controller.getWritableDatabase();
        StringBuilder labelList = new StringBuilder();
        if (labels != null) {
            for (int i = 0; i < labels.size(); i++)
                labelList.append(labels.get(i)).append(i == labels.size() - 1 ? "" : ",");
        }
        ContentValues values = new ContentValues();
        values.put(NOTES_TITLE, title);
        values.put(NOTES_CONTENT, content);
        values.put(NOTES_LABELS, labelList.toString());
        long id = db.insert(String.format(Locale.getDefault(), "'%d'", notebookId), null, values);
        if (id != -1);
        else;
    }

    public static List<NoteModel> getAllNotes(DatabaseController controller, long id) {
        SQLiteDatabase db = controller.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT " +
                _ID + ", " +
                NOTES_TITLE + ", " +
                NOTES_CONTENT + ", " +
                NOTES_LABELS + ", " +
                _DELETED + ", " +
                _CREATED_AT + ", " +
                _UPDATED_AT + " FROM '" +
                id + "';", null);

        List<NoteModel> noteModels = new ArrayList<>();
        if (cs.moveToFirst()) {
            do {
                NoteModel note = new NoteModel(cs.getInt(cs.getColumnIndex(_ID)), cs.getString(cs.getColumnIndex(NOTES_TITLE)), cs.getString(cs.getColumnIndex(NOTES_CONTENT)), null, null, null, cs.getInt(cs.getColumnIndex(_DELETED)) == 1);
                noteModels.add(note);
            } while (cs.moveToNext());
        }
        return noteModels;
    }
}
