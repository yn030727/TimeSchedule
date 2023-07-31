package com.example.module_calendar.model.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "Schedule_data")
@TypeConverters(ScheduleDayConverter.class)
public class Schedule_data {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "day" , typeAffinity = ColumnInfo.TEXT)
    public String day;


    public List<Schedule_Day> schedule_days;

    public Schedule_data(String day , List<Schedule_Day> list){
        this.day = day;
        this.schedule_days = list;
    }

    public Schedule_data(){}

    public void setDay(String day) {
        this.day = day;
    }

    public void setSchedule_days(List<Schedule_Day> schedule_days) {
        this.schedule_days = schedule_days;
    }

    public List<Schedule_Day> getSchedule_days() {
        return schedule_days;
    }

    public String getDay() {
        return day;
    }

    public static class Schedule_Day{
        public int image;
        public String text;
        public boolean complete;

        public Schedule_Day(String text , int image , boolean complete){
            this.complete = complete;
            this.text = text;
            this.image = image;
        }

        public int getImage() {
            return image;
        }

        public String getText() {
            return text;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }

        public boolean isComplete() {
            return complete;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
