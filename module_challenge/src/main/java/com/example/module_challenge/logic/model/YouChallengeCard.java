package com.example.module_challenge.logic.model;

//已经接下挑战后的卡片上的显示的信息
public class YouChallengeCard {
    //挑战的名称
    String u_challenge_name;
    //挑战的名言
    String u_challenge_saying;
    //挑战界面的图片
    int u_challenge_image;
    //当前挑战卡片按钮点击后跳转的界面
    String u_to_challenge_activity;
    //当前挑战的进度
    int u_challenge_progress;

    public YouChallengeCard(){}
    public YouChallengeCard(String u_challenge_name , int u_challenge_progress, String u_to_challenge_activity , int u_challenge_image){
        this.u_challenge_image = u_challenge_image;
        this.u_challenge_name = u_challenge_name;
        this.u_challenge_progress = u_challenge_progress;
        this.u_to_challenge_activity = u_to_challenge_activity;
    }

    public int getU_challenge_image() {
        return u_challenge_image;
    }

    public String getU_challenge_name() {
        return u_challenge_name;
    }

    public String getU_challenge_saying() {
        return u_challenge_saying;
    }

    public String getU_to_challenge_activity() {
        return u_to_challenge_activity;
    }

    public void setU_challenge_image(int u_challenge_image) {
        this.u_challenge_image = u_challenge_image;
    }

    public void setU_challenge_name(String u_challenge_name) {
        this.u_challenge_name = u_challenge_name;
    }

    public void setU_challenge_saying(String u_challenge_saying) {
        this.u_challenge_saying = u_challenge_saying;
    }

    public void setU_to_challenge_activity(String u_to_challenge_activity) {
        this.u_to_challenge_activity = u_to_challenge_activity;
    }

    public int getU_challenge_progress() {
        return u_challenge_progress;
    }

    public void setU_challenge_progress(int u_challenge_progress) {
        this.u_challenge_progress = u_challenge_progress;
    }
}
