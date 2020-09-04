package com.bukonudakonusalim.takenotes.data.model;

public class LabelModel {

    private int id;
    private String name;
    private String color;
    private boolean isActive = false;

    public LabelModel(int id, String name, String color, boolean isActive) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "LabelModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
