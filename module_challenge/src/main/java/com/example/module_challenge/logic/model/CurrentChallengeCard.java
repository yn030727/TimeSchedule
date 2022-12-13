package com.example.module_challenge.logic.model;

//挑战卡片上的信息类
public class CurrentChallengeCard {
    //挑战的名称
    String challenge_name;
    //挑战的名言
    String challenge_saying;
    //挑战界面的图片
    int challenge_image;
    //挑战项目的参与人数(当前变量不一定使用)
    int challenge_personNum;

    //构造方法
    public CurrentChallengeCard(){}
    public CurrentChallengeCard(String challenge_name , String challenge_saying , int challenge_image){
        this.challenge_image = challenge_image;
        this.challenge_name = challenge_name;
        this.challenge_saying = challenge_saying;
    }
    public CurrentChallengeCard(String challenge_name , String challenge_saying , int challenge_image , int challenge_personNum){
        this.challenge_image = challenge_image;
        this.challenge_name = challenge_name;
        this.challenge_saying = challenge_saying;
        this.challenge_personNum = challenge_personNum;
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
