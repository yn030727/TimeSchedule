package com.example.module_challenge.model.data;


//Room数据库中的一张表
//里面存放的是，关于当前挑战的各类信息
//id ： 是哪一个挑战
//challenge ： 是否开始挑战
//progress ： 挑战进行的进度
//complete ： 已经完成
//其中challenge ， complete 中 1表示已经完成 ， 0表示未完成

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Challenge_DataEntity")
public class challenge_data {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id" , typeAffinity = ColumnInfo.INTEGER)
    public int id ;

    @ColumnInfo(name = "challenge" , typeAffinity = ColumnInfo.INTEGER)
    public int challenge;

    @ColumnInfo(name = "progress" , typeAffinity = ColumnInfo.INTEGER)
    public int progress;

    @ColumnInfo(name = "complete" , typeAffinity = ColumnInfo.INTEGER)
    public int complete;

    public challenge_data(int id , int challenge , int progress , int complete){
        this.id = id;
        this.challenge = challenge;
        this.progress = progress;
        this.complete = complete;
    }

    public int getChallenge() {
        return challenge;
    }

    public int getComplete() {
        return complete;
    }

    public int getProgress() {
        return progress;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "challenge_data{" +
                "id='" + id + '\'' +
                ", challenge=" + challenge +
                ", progress=" + progress +
                ", complete=" + complete +
                '}';
    }
}
