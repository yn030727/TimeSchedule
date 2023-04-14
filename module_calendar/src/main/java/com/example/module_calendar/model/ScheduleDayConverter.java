package com.example.module_calendar.model;

import androidx.room.TypeConverter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ScheduleDayConverter {

    @TypeConverter
    public String objectToString(List<Schedule_data.Schedule_Day> list){
        return GsonInstance.getInstance().getGson().toJson(list);
    }

    @TypeConverter
    public List<Schedule_data.Schedule_Day> stringToObject(String json){
        Type listType = new TypeToken<List<Schedule_data.Schedule_Day>>(){}.getType();
        return GsonInstance.getInstance().getGson().fromJson(json , listType);
    }
}
