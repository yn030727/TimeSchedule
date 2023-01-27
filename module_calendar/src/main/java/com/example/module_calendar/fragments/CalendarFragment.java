package com.example.module_calendar.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.module_calendar.R;
import com.example.module_calendar.model.Article;
import com.example.module_calendar.ui.BaseActivity;
import com.example.module_calendar.ui.GroupItemDecoration;
import com.example.module_calendar.ui.GroupRecyclerView;
import com.example.module_calendar.ui.TargetAdapter;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/calendar/CalendarFragment")
public class CalendarFragment extends Fragment implements
        CalendarView.OnCalendarSelectListener ,
        CalendarView.OnYearChangeListener,
        View.OnClickListener{


    //0.定义变量
    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    //RecyclerView mRecyclerView;
    List<String> list;
    TextView calendar_week_textview;
    TextView calendar_week_textview2;
    ImageView calendar_title_imageView;
    ImageView calendar_title_add;
    java.util.Calendar calendar1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar,container,false);

        mTextMonthDay = view.findViewById(R.id.tv_month_day);
        mTextYear = view.findViewById(R.id.tv_year);
        mTextLunar = view.findViewById(R.id.tv_lunar);
        mRelativeTool = view.findViewById(R.id.rl_tool);
        mCalendarView = view.findViewById(R.id.calendarView);
        mCalendarLayout = view.findViewById(R.id.calendarLayout);
        mTextCurrentDay = view.findViewById(R.id.tv_current_day);
        calendar_week_textview = view.findViewById(R.id.calendar_week_textview);
        calendar_week_textview2 = view.findViewById(R.id.calendar_week_textview2);
        calendar_title_imageView = view.findViewById(R.id.calendar_title_imageView);
        calendar_title_add = view.findViewById(R.id.calendar_title_add);
        calendar1 = java.util.Calendar.getInstance();




        //点击月份切换到年份选择界面
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mCalendarLayout.isExpand()) {
                    mCalendarLayout.expand();
                    return;
                }

                mCalendarView.showYearSelectLayout(mYear);

                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });




        view.findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        calendar_week_textview.setText(JudgeWeek(calendar1.get(java.util.Calendar.DAY_OF_WEEK)));
        calendar_week_textview2.setText(JudgeWeek2(calendar1.get(java.util.Calendar.DAY_OF_WEEK)));
        Log.d("Ning_Module_Calendar" , "week is "+JudgeWeek2(calendar1.get(java.util.Calendar.DAY_OF_WEEK )));

       //initData

        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        Log.d("Ning_Module_Calendar" , " year is "+ year + "  month is " + month);


        list = new ArrayList<>();
        //mRecyclerView = view.findViewById(R.id.recyclerView);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        //mRecyclerView.setAdapter(new TargetAdapter(list));
        //mRecyclerView.notifyDataSetChanged();

//        RecyclerViewHeader recyclerViewHeader = view.findViewById(R.id.header);
//        recyclerViewHeader.attachTo(mRecyclerView);
        return view;
    }









    //2.相关方法介绍

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());

        return calendar;
    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        calendar_week_textview.setText(JudgeWeek(calendar.getWeek()));
        calendar_week_textview2.setText(JudgeWeek3(calendar.getWeek()));
        mYear = calendar.getYear();
        Log.d("Ning_Module_Calendar" , "week is "+JudgeWeek2(calendar1.get(java.util.Calendar.DAY_OF_WEEK ) - 1));
    }

    @Override
    public void onYearChange(int year) {
    }

    @Override
    public void onClick(View v) {

    }



    public void initData(){
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
    }


    public String JudgeWeek(int week){
        if(week == 1){
            return "月曜太阴" ;
        }else if(week == 2){
            return "火曜萤惑" ;
        }else if(week == 3){
            return "水曜辰星";
        }else if(week == 4){
            return "木曜岁星";
        }else if(week == 5){
            return "金曜太白";
        }else if(week == 6){
            return "土曜镇星";
        }else{
            return "日曜太阳";
        }
    }
    public String JudgeWeek2(int week){
        if(week == 2){
            return "周一" ;
        }else if(week == 3){
            return "周二" ;
        }else if(week == 4){
            return "周三";
        }else if(week == 5){
            return "周四";
        }else if(week == 6){
            return "周五";
        }else if(week == 7){
            return "周六";
        }else{
            return "周日";
        }
    }

    public String JudgeWeek3(int week){
        if(week == 1){
            return "周一" ;
        }else if(week == 2){
            return "周二" ;
        }else if(week == 3){
            return "周三";
        }else if(week == 4){
            return "周四";
        }else if(week == 5){
            return "周五";
        }else if(week == 6){
            return "周六";
        }else{
            return "周日";
        }
    }




}
