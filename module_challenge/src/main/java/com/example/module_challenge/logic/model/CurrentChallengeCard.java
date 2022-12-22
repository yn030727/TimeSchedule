package com.example.module_challenge.logic.model;

import android.app.Activity;

//挑战卡片上的信息类
public class CurrentChallengeCard {
    //挑战的名称
    String challenge_name;
    //挑战的名言
    String challenge_saying;
    //挑战界面的图片
    int challenge_image;
    //挑战界面的右下角图片
    int challenge_rightImage;
    //当前挑战卡片按钮点击后跳转的界面
    String to_challenge_activity;

    //挑战项目的参与人数(当前变量不一定使用)
    int challenge_personNum;


    //构造方法
    public CurrentChallengeCard(){}
    public CurrentChallengeCard(String challenge_name , String challenge_saying , int challenge_image , int challenge_rightImage){
        this.challenge_image = challenge_image;
        this.challenge_name = challenge_name;
        this.challenge_saying = challenge_saying;
        this.challenge_rightImage = challenge_rightImage;
    }
    public CurrentChallengeCard(String challenge_name , String challenge_saying , int challenge_image , int challenge_personNum , int challenge_rightImage, String To_challenge_activity){
        this.challenge_image = challenge_image;
        this.challenge_name = challenge_name;
        this.challenge_saying = challenge_saying;
        this.challenge_personNum = challenge_personNum;
        this.challenge_rightImage = challenge_rightImage;
        this.to_challenge_activity = To_challenge_activity;
    }
    public CurrentChallengeCard(String challenge_name , String challenge_saying , int challenge_image , int challenge_personNum ,  String To_challenge_activity){
        this.challenge_image = challenge_image;
        this.challenge_name = challenge_name;
        this.challenge_saying = challenge_saying;
        this.challenge_personNum = challenge_personNum;
        this.to_challenge_activity =  To_challenge_activity;;
    }


    public int getChallenge_image() {
        return challenge_image;
    }

    public int getChallenge_personNum() {
        return challenge_personNum;
    }

    public String getChallenge_name() {
        return challenge_name;
    }

    public String getChallenge_saying() {
        return challenge_saying;
    }

    public int getChallenge_rightImage() {
        return challenge_rightImage;
    }

    public String getTo_challenge_activity() {
        return to_challenge_activity;
    }

    public void setTo_challenge_activity(String to_challenge_activity) {
        this.to_challenge_activity = to_challenge_activity;
    }

    public void setChallenge_rightImage(int challenge_rightImage) {
        this.challenge_rightImage = challenge_rightImage;
    }

    public void setChallenge_image(int challenge_image) {
        this.challenge_image = challenge_image;
    }

    public void setChallenge_name(String challenge_name) {
        this.challenge_name = challenge_name;
    }

    public void setChallenge_personNum(int challenge_personNum) {
        this.challenge_personNum = challenge_personNum;
    }

    public void setChallenge_saying(String challenge_saying) {
        this.challenge_saying = challenge_saying;
    }
}
