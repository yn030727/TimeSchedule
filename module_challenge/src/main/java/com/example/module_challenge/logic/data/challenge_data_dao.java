package com.example.module_challenge.logic.data;


//针对上面的挑战Entity，我们需要定义一个dao接口文件同来对Entity进行访问


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface challenge_data_dao {

    @Insert
    void insertChallenge_data(challenge_data... challengeData);

    @Delete
    void deleteChallenge_data(challenge_data... challengeData);

    @Update
    void updateChallenge_data(challenge_data... challengeData);

    //在Challenge_DataEntity这张表中，获取所有数据
    @Query("SELECT * FROM Challenge_DataEntity")
    List<challenge_data> getChallengeList();

    //在Challenge_DataEntity这张表中根据id查找想要找的数据
    @Query("SELECT * FROM Challenge_DataEntity WHERE id =:id")
    challenge_data getChallengeById(Integer... id);

}
