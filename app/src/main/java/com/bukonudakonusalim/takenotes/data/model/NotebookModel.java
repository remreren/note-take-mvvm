package com.bukonudakonusalim.takenotes.data.model;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import org.joda.time.DateTime;

public class NotebookModel {

    private int id;
    private String notebookName;
    private String notebookDescription;
    private String notebookColor;
    private int notebookType;
    private boolean isDeleted;
    private DateTime createdAt;
    private DateTime updatedAt;

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

    @BindingAdapter("dateBinding")
    public static void bindDateBinding(@NonNull TextView textView, String date) {

    }
}
