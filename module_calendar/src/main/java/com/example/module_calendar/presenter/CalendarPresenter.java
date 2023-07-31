package com.example.module_calendar.presenter;

import com.example.module_calendar.model.data.CalendarSchedule;

import java.util.ArrayList;

public class CalendarPresenter implements CalendarContract.Presenter {

    private CalendarContract.View view;

    public CalendarPresenter(CalendarContract.View view){
        this.view = view;
    }

    ArrayList<CalendarSchedule> arrayList = new ArrayList<>();
    @Override
    public void loadCurrentDaySchedule(String year, String month, String day) {
        //获取到日期
        //得知View层切换了日期
        //访问Room数据库，将当天的打卡内容存放到集合当中
        arrayList = getCurrentDayArrayList(year , month , day);
        view.showCurrentDaySchedule(arrayList);
    }

    private ArrayList<CalendarSchedule> getCurrentDayArrayList(String year , String month , String day){
        return null;
    }

}
