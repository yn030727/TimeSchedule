package com.example.module_editscheudle.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_editscheudle.model.schedule;

import java.util.ArrayList;

public class EditScheduleAdapter extends RecyclerView.Adapter<EditScheduleViewHolder>{
    ArrayList<schedule> scheduleArrayList;
    ArrayList<schedule> curArrayList;

    public EditScheduleAdapter(ArrayList<schedule> scheduleArrayList , ArrayList<schedule> curArrayList){
        this.curArrayList = curArrayList;
        this.scheduleArrayList =scheduleArrayList;

    }
    @NonNull
    @Override
    public EditScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EditScheduleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class EditScheduleViewHolder extends RecyclerView.ViewHolder{

    public EditScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
