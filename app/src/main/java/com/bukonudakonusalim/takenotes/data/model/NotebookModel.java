package com.bukonudakonusalim.takenotes.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bukonudakonusalim.takenotes.utils.DatabaseController;

import static com.bukonudakonusalim.takenotes.utils.DatabaseController.*;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class NotebookModel {

    private int id;
    private String notebookName;
    private String notebookDescription;
    private String notebookColor;
    private int notebookType;
    private boolean isDeleted;
    private DateTime createdAt;
    private DateTime updatedAt;
    private int size;

    public NotebookModel() {

    }

    public NotebookModel(int id, String notebookName, String notebookDescription, String notebookColor, int notebookType, boolean isDeleted, DateTime createdAt, DateTime updatedAt) {
        this.id = id;
        this.notebookName = notebookName;
        this.notebookDescription = notebookDescription;
        this.notebookColor = notebookColor;
        this.notebookType = notebookType;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public NotebookModel(String notebookName, String notebookDescription, String notebookColor, int notebookType) {
        this.notebookName = notebookName;
        this.notebookDescription = notebookDescription;
        this.notebookColor = notebookColor;
        this.notebookType = notebookType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public String getNotebookDescription() {
        return notebookDescription;
    }

    public void setNotebookDescription(String notebookDescription) {
        this.notebookDescription = notebookDescription;
    }

    public String getNotebookColor() {
        return notebookColor;
    }

    public void setNotebookColor(String notebookColor) {
        this.notebookColor = notebookColor;
    }

    public int getNotebookType() {
        return notebookType;
    }

    public void setNotebookType(int notebookType) {
        this.notebookType = notebookType;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public void save(DatabaseController controller) {
        new Thread(() -> {
            SQLiteDatabase db = controller.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NOTEBOOKS_NAME, notebookName);
            values.put(NOTEBOOKS_DESCRIPTION, notebookDescription);
            values.put(NOTEBOOKS_COLOR, notebookColor);
            values.put(NOTEBOOKS_TYPE, notebookType);
            long id = db.insert(TABLE_NOTEBOOKS_NAME, null, values);
            if (id != -1)
                controller.createNotebookTable(id);
        }).start();
    }

    public static List<NotebookModel> getAllNotebooks(DatabaseController controller) {
        SQLiteDatabase db = controller.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT " +
                _ID + ", " +
                NOTEBOOKS_NAME + ", " +
                NOTEBOOKS_DESCRIPTION + ", " +
                NOTEBOOKS_COLOR + ", " +
                NOTEBOOKS_TYPE + ", " +
                NOTEBOOKS_DELETED + ", " +
                _CREATED_AT + ", " +
                _UPDATED_AT + " FROM " +
                TABLE_NOTEBOOKS_NAME + ";", null);

        List<NotebookModel> notebookModels = new ArrayList<>();
        if (cs.moveToFirst()) {
            do {
                NotebookModel notebook = new NotebookModel(cs.getInt(cs.getColumnIndex(_ID)), cs.getString(cs.getColumnIndex(NOTEBOOKS_NAME)), cs.getString(cs.getColumnIndex(NOTEBOOKS_DESCRIPTION)), cs.getString(cs.getColumnIndex(NOTEBOOKS_COLOR)), cs.getInt(cs.getColumnIndex(NOTEBOOKS_TYPE)), cs.getInt(cs.getColumnIndex(NOTEBOOKS_DELETED)) == 1, null, null);
                notebookModels.add(notebook);
            } while (cs.moveToNext());
        }
        return notebookModels;
    }
}
