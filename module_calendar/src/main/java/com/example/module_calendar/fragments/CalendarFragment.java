package com.example.module_calendar.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_calendar.R;
import com.haibin.calendarview.CalendarView;

@Route(path = "/calendar/CalendarFragment")
public class CalendarFragment extends Fragment {
    CalendarView calendar_title_calendarView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar,container,false);
        calendar_title_calendarView = view.findViewById(R.id.calendar_title_calendarView);

        return view;
    }
}
