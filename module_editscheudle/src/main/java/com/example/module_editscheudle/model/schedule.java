package com.example.module_editscheudle.model;

//计划的类
public class schedule {
    int image;
    int complete;
    int leftImage;
    String text;

    public schedule(String text , int image , int leftImage , int complete){
        this.complete = complete;
        this.image = image;
        this.text = text;
        this.leftImage = leftImage;
    }

    public int getComplete() {
        return complete;
    }

    public int getImage() {
        return image;
    }

    public int getLeftImage() {
        return leftImage;
    }

    public String getText() {
        return text;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setLeftImage(int leftImage) {
        this.leftImage = leftImage;
    }

    public void setText(String text) {
        this.text = text;
    }
}
