package com.example.module_calendar.presenter;

import com.example.module_calendar.model.data.CalendarSchedule;

import java.util.ArrayList;

public interface CalendarContract{
    ArrayList<CalendarSchedule> calendarScheduleArrayList = new ArrayList<>();

    interface View{
        //展示当天日历中的计划
        void showCurrentDaySchedule(ArrayList<CalendarSchedule> arrayList);
    }

    //注入到Presenter中进行业务逻辑的处理
    interface Presenter{
        void loadCurrentDaySchedule(String year , String month , String day);
    }
}
