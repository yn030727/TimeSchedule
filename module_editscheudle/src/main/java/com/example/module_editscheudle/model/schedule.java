package com.example.module_editscheudle.model;

//计划的类
public class schedule {
    int image;
    boolean complete;
    int rightImage;
    String text;

    public schedule(String text , int image , int rightImage ,  boolean complete){
        this.complete = complete;
        this.image = image;
        this.text = text;
        this.rightImage = rightImage;
    }

    public  boolean getComplete() {
        return complete;
    }

    public int getImage() {
        return image;
    }

    public int getRightImage() {
        return rightImage;
    }

    public String getText() {
        return text;
    }

    public void setComplete( boolean complete) {
        this.complete = complete;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setRightImage(int rightImage) {
        this.rightImage = rightImage;
    }

    public void setText(String text) {
        this.text = text;
    }
}
