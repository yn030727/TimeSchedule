package com.example.module_challenge.model.symbol;


//挑战打卡信息，七个挑战通用
public class ChallengePunch {

    //当天挑战的日子
    String challenge_day_carrying;
    //当天挑战的介绍图片
    int challenge_image_carrying;
    //当天挑战的名称
    String challenge_name_carrying;
    //当天挑战的介绍
    String challenge_introduce_carrying;
    //挑战是否被选择
    int challenge_complete;

    public ChallengePunch(String challenge_day_carrying , int challenge_image_carrying , String challenge_name_carrying, String challenge_introduce_carrying ){
        this.challenge_day_carrying = challenge_day_carrying;
        this.challenge_image_carrying = challenge_image_carrying;
        this.challenge_introduce_carrying = challenge_introduce_carrying;
        this.challenge_name_carrying = challenge_name_carrying;

    }

    public int getChallenge_image_carrying() {
        return challenge_image_carrying;
    }

    public String getChallenge_introduce_carrying() {
        return challenge_introduce_carrying;
    }

    public String getChallenge_name_carrying() {
        return challenge_name_carrying;
    }

    public String getChallenge_day_carrying() {
        return challenge_day_carrying;
    }

    public int getChallenge_complete() {
        return challenge_complete;
    }

    public void setChallenge_complete(int challenge_complete) {
        this.challenge_complete = challenge_complete;
    }

    public void setChallenge_day_carrying(String challenge_day_carrying) {
        this.challenge_day_carrying = challenge_day_carrying;
    }

    public void setChallenge_image_carrying(int challenge_image_carrying) {
        this.challenge_image_carrying = challenge_image_carrying;
    }

    public void setChallenge_introduce_carrying(String challenge_introduce_carrying) {
        this.challenge_introduce_carrying = challenge_introduce_carrying;
    }

    public void setChallenge_name_carrying(String challenge_name_carrying) {
        this.challenge_name_carrying = challenge_name_carrying;
    }
}
