package com.example.module_calendar.model.data;

public class CalendarSchedule {
    int image;
    boolean complete;
    String text;
    public CalendarSchedule(String text , int image , boolean complete){
        this.complete = complete;
        this.image = image;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
