package com.android.to_doapplication;

public class TodoList {
    String color, date, title, note;

    public TodoList() {
    }

    public TodoList(String color, String date, String title, String note) {
        this.color = color;
        this.date = date;
        this.title = title;
        this.note = note;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
