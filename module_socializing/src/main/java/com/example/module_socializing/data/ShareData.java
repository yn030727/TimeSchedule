package com.example.module_socializing.data;


public class ShareData {

    public int head; //头像
    public String name; //账号名
    public String text; //文本内容
    public int image;   //图片
    public String time; //发布时间


    public ShareData(int head , String name , String text , int image , String time){
        this.head = head;
        this.name = name;
        this.text = text;
        this.image = image;
        this.time = time;
    }
    public ShareData(int head , String name , String text , String time){
        this.head = head;
        this.name = name;
        this.text = text;
        this.image = -1;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public int getHead() {
        return head;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
