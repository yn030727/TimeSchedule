package com.example.module_calendar.ui;

import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_calendar.R;
import com.example.module_calendar.model.CalendarSchedule;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarScheduleAdapter extends  RecyclerView.Adapter<CalendarScheduleViewHolder>{
    ArrayList<CalendarSchedule> scheduleArrayList;
    Typeface typeface;
    HashMap<String , Boolean> scheduleStateHashMap;

    public CalendarScheduleAdapter(ArrayList<CalendarSchedule> scheduleArrayList , Typeface typeface , HashMap<String , Boolean> scheduleStateHashMap){
        this.scheduleArrayList = scheduleArrayList;
        this.typeface = typeface;
        this.scheduleStateHashMap = scheduleStateHashMap;
    }

    @NonNull
    @Override
    public CalendarScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_recyclerview_calendar_schedule,parent,false);
        CalendarScheduleViewHolder calendarScheduleViewHolder = new CalendarScheduleViewHolder(view);
        return calendarScheduleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarScheduleViewHolder holder, int position) {
        CalendarSchedule calendarSchedule = scheduleArrayList.get(position);

        //更新UI
        holder.calendar_schedule_name.setText(calendarSchedule.getText());
        holder.calendar_schedule_image.setImageResource(calendarSchedule.getImage());
        holder.calendar_schedule_name.setTypeface(typeface);

        //点击事件，表示事情的完成
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ning_module_calendar", "CalendarScheduleAdapter constraintLayout onClick");
                if(calendarSchedule.getComplete()){
                    //如果当前计划是完成的情况
                    //那么取消完成情况
                    holder.constraintLayout.setBackgroundResource(R.drawable.calendar_layout_shape3);
                    scheduleStateHashMap.put(calendarSchedule.getText(),false);
                    calendarSchedule.setComplete(false);
                    Log.d("Ning_module_calendar","CalendarScheduleAdapter " + calendarSchedule.getText() + "改变了状态为未完成");
                }else{
                    //如果当前计划还没有完成
                    //变成完成，并且改变颜色
                    holder.constraintLayout.setBackgroundResource(R.drawable.calendar_layout_shape4);
                    scheduleStateHashMap.put(calendarSchedule.getText(),true);
                    calendarSchedule.setComplete(true);
                    Log.d("Ning_module_calendar","CalendarScheduleAdapter " + calendarSchedule.getText() + "改变了状态为已完成");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return scheduleArrayList.size();
    }
}

class CalendarScheduleViewHolder extends RecyclerView.ViewHolder{
    ConstraintLayout constraintLayout;
    ImageView calendar_schedule_image;
    TextView calendar_schedule_name;
    public CalendarScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.Calendar_constraint);
        calendar_schedule_image = itemView.findViewById(R.id.Calendar_recyclerview_image);
        calendar_schedule_name = itemView.findViewById(R.id.Calendar_recyclerview_textview);
    }
}