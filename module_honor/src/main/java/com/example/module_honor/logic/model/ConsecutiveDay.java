package com.example.module_honor.logic.model;

import android.widget.ImageView;

public class ConsecutiveDay {
    int consecutiveDay_image;
    String consecutiveDay_name;
    String consecutiveDay_description;
    public ConsecutiveDay(int consecutiveDay_image , String consecutiveDay_name , String consecutiveDay_description){
        this.consecutiveDay_name = consecutiveDay_name;
        this.consecutiveDay_image = consecutiveDay_image;
        this. consecutiveDay_description = consecutiveDay_description;
    }

    public int getConsecutiveDay_image() {
        return consecutiveDay_image;
    }

    public String getConsecutiveDay_description() {
        return consecutiveDay_description;
    }

    public String getConsecutiveDay_name() {
        return consecutiveDay_name;
    }

    public void setConsecutiveDay_description(String consecutiveDay_description) {
        this.consecutiveDay_description = consecutiveDay_description;
    }

    public void setConsecutiveDay_image(int consecutiveDay_image) {
        this.consecutiveDay_image = consecutiveDay_image;
    }

    public void setConsecutiveDay_name(String consecutiveDay_name) {
        this.consecutiveDay_name = consecutiveDay_name;
    }
}
