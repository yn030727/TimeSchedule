package com.example.module_calendar.model;


import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Schedule_data.class} , version = 1 ,exportSchema = false)
public abstract class Schedule_Database extends RoomDatabase {
    private static final String DATABASE_NAME = "schedule_db";
    public static Schedule_Database databaseInstance;

    public static synchronized Schedule_Database getInstance(Context context){
        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext() , Schedule_Database.class , DATABASE_NAME).build();
        }
        return databaseInstance;
    }
    public abstract Schedule_Data_Dao getSchedule_data_dao();
}
