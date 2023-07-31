package com.example.module_calendar.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_calendar.R;
import com.example.module_calendar.model.data.CalendarSchedule;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarScheduleAdapter extends  RecyclerView.Adapter<CalendarScheduleViewHolder>{
    ArrayList<CalendarSchedule> scheduleArrayList;
    Typeface typeface;
    HashMap<String , Boolean> scheduleStateHashMap;
    ImageView title;
    HashMap<String , Boolean> receiveHashMap;

    public CalendarScheduleAdapter(ArrayList<CalendarSchedule> scheduleArrayList , Typeface typeface , HashMap<String , Boolean> scheduleStateHashMap , ImageView title , HashMap<String , Boolean> receiveHashMap){
        this.scheduleArrayList = scheduleArrayList;
        this.typeface = typeface;
        this.scheduleStateHashMap = scheduleStateHashMap;
        this.title = title;
        this.receiveHashMap = receiveHashMap;
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
    public void onBindViewHolder(@NonNull CalendarScheduleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CalendarSchedule calendarSchedule = scheduleArrayList.get(position);

        //更新UI
        holder.calendar_schedule_name.setText(calendarSchedule.getText());
        holder.calendar_schedule_image.setImageResource(calendarSchedule.getImage());
        holder.calendar_schedule_name.setTypeface(typeface);
        int randomColor = Color.rgb(111 , 24 , 32);
        holder.constraintLayout_biaoqian.setBackgroundColor(randomColor);
        if(calendarSchedule.getComplete()){
            holder.constraintLayout.setBackgroundResource(R.drawable.calendar_layout_shape4);
        }else{
            holder.constraintLayout.setBackgroundResource(R.drawable.calendar_layout_shape3);
        }

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
                    if(scheduleStateHashMap.size() != 0){
                        //添加后变为1，表示之前是已经全部完成了
                        title.setImageResource(R.drawable.calendar_title_unhappyface2);
                    }
                    Log.d("Ning_module_calendar","CalendarScheduleAdapter " + calendarSchedule.getText() + "改变了状态为未完成");
                }else{
                    //如果当前计划还没有完成
                    //变成完成，并且改变颜色
                    holder.constraintLayout.setBackgroundResource(R.drawable.calendar_layout_shape4);
                    //计划，完成先从Map集合中删除
                    scheduleStateHashMap.remove(calendarSchedule.getText());
                    calendarSchedule.setComplete(true);
                    if(scheduleStateHashMap.size() == 0){
                        title.setImageResource(R.drawable.calendar_title_happyface2);
                        Toast.makeText(v.getContext(), "您已经完成了今日所有的目标" , Toast.LENGTH_SHORT).show();
                    }
                    Log.d("Ning_module_calendar","CalendarScheduleAdapter " + calendarSchedule.getText() + "改变了状态为已完成");
                }
            }
        });

        //点击事件，表示任务的取消
        holder.calendar_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ning_module_calendar" , "delete ");
                calendarSchedule.setComplete(false);
                scheduleStateHashMap.remove(calendarSchedule.getText());
                scheduleArrayList.remove(position);
                if(receiveHashMap != null){
                    receiveHashMap.put(calendarSchedule.getText() , false);
                }
                notifyDataSetChanged();
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
    ConstraintLayout constraintLayout_biaoqian;
    ImageView calendar_delete;

    public CalendarScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.Calendar_constraint);
        calendar_schedule_image = itemView.findViewById(R.id.Calendar_recyclerview_image);
        calendar_schedule_name = itemView.findViewById(R.id.Calendar_recyclerview_textview);
        constraintLayout_biaoqian = itemView.findViewById(R.id.Calendar_recyclerview_biaoqian);
        calendar_delete = itemView.findViewById(R.id.Calendar_recyclerview_delete);
    }
}