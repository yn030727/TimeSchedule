package com.example.module_calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

//可以跳转组件
@Route(path="/module_calendar/module_calendar1")
public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Ning","CalendarActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }
}