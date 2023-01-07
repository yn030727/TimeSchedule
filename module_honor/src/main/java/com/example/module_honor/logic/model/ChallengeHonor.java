package com.example.module_honor.logic.model;

public class ChallengeHonor {
    String challenge_honor_name;
    int challenge_honor_image;
    public ChallengeHonor(String challenge_honor_name , int challenge_honor_image){
        this.challenge_honor_image = challenge_honor_image;
        this.challenge_honor_name = challenge_honor_name;
    }

    public int getChallenge_honor_image() {
        return challenge_honor_image;
    }

    public String getChallenge_honor_name() {
        return challenge_honor_name;
    }

    public void setChallenge_honor_image(int challenge_honor_image) {
        this.challenge_honor_image = challenge_honor_image;
    }

    public void setChallenge_honor_name(String challenge_honor_name) {
        this.challenge_honor_name = challenge_honor_name;
    }
}
