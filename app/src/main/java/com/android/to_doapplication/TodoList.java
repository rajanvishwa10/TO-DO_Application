package com.android.to_doapplication;

public class TodoList {
    String color, date, title, note;
    boolean pin;

    public TodoList() {
    }

    public TodoList(String color, String date, String title, String note, boolean pin) {
        this.color = color;
        this.date = date;
        this.title = title;
        this.note = note;
        this.pin = pin;
    }

    public boolean isPin() {
        return pin;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
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
