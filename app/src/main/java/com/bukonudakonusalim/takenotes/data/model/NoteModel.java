package com.bukonudakonusalim.takenotes.data.model;

import org.joda.time.DateTime;

import java.util.List;

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
}
