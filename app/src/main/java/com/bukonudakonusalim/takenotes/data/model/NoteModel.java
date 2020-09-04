package com.bukonudakonusalim.takenotes.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bukonudakonusalim.takenotes.data.DataHolder;
import com.bukonudakonusalim.takenotes.utils.DatabaseController;
import com.bukonudakonusalim.takenotes.utils.TimeUtils;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.bukonudakonusalim.takenotes.utils.DatabaseController.*;

public class NoteModel {

    private int id;
    private String title;
    private String content;
    private List<LabelModel> labels;
    private DateTime createdAt;
    private DateTime updatedAt;
    private boolean isDeleted;

    public NoteModel(int id, String title, String content, List<LabelModel> labels, DateTime createdAt, DateTime updatedAt, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.labels = labels;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public NoteModel(String title, String content, List<LabelModel> labels) {
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

    public List<LabelModel> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelModel> labels) {
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

    public void save(DatabaseController controller, int notebookId) {
        SQLiteDatabase db = controller.getWritableDatabase();
        String labels = arrayToString(getLabels());
        ContentValues values = new ContentValues();
        values.put(NOTES_TITLE, title);
        values.put(NOTES_CONTENT, content);
        values.put(NOTES_LABELS, labels);
        long id = db.insert(String.format(Locale.getDefault(), "'notes_%d'", notebookId), null, values);
        if (id != -1) ;
        else ;
    }

    private String arrayToString(List<LabelModel> labels) {
        StringBuilder labelString = new StringBuilder();
        for (int i = 0; i < labels.size(); i++) {
            labelString.append(labels.get(i).getColor());
            if (i < labels.size() - 1) {
                labelString.append(",");
            }
        }
        return labelString.toString();
    }

    private static List<LabelModel> stringToArray(String labels, List<LabelModel> labelList) {
        List<LabelModel> label = new ArrayList<>();
        String[] labelColors = labels.split(",");

        for (String color: labelColors) {
            for (LabelModel labelt: labelList) {
                if (labelt.getColor().equals(color)) {
                    label.add(labelt);
                    break;
                }
            }
        }

        return label;
    }

    public static List<NoteModel> getAllNotes(DatabaseController controller, int id) {
        SQLiteDatabase db = controller.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT " +
                _ID + ", " +
                NOTES_TITLE + ", " +
                NOTES_CONTENT + ", " +
                NOTES_LABELS + ", " +
                _DELETED + ", " +
                _CREATED_AT + ", " +
                _UPDATED_AT + " FROM 'notes_" +
                id + "';", null);

        List<NoteModel> noteModels = new ArrayList<>();
        if (cs.moveToFirst()) {
            do {
                NoteModel note = new NoteModel(cs.getInt(cs.getColumnIndex(_ID)), cs.getString(cs.getColumnIndex(NOTES_TITLE)), cs.getString(cs.getColumnIndex(NOTES_CONTENT)), stringToArray(cs.getString(cs.getColumnIndex(NOTES_LABELS)), DataHolder.getInstance().getNotebooks().get(id).getLabels()), TimeUtils.timeStringToDateTime(cs.getString(cs.getColumnIndex(_CREATED_AT))), TimeUtils.timeStringToDateTime(cs.getString(cs.getColumnIndex(_UPDATED_AT))), cs.getInt(cs.getColumnIndex(_DELETED)) == 1);
                noteModels.add(note);
            } while (cs.moveToNext());
        }

        cs.close();

        return noteModels;
    }
}
