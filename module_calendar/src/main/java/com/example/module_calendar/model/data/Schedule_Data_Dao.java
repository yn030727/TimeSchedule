package com.example.module_calendar.model.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Schedule_Data_Dao {

    @Insert
    void insert_Schedule(Schedule_data schedule_data);

    @Delete
    void delete_Schedule(Schedule_data schedule_data);

    @Update
    void update_Schedule(Schedule_data schedule_data);

    @Query("SELECT * FROM Schedule_data")
    List<Schedule_data> getSchedule_Data_List();

    @Query("SELECT * FROM Schedule_data WHERE day = :day")
    Schedule_data getSchedule_Data_ById(String day);


}
