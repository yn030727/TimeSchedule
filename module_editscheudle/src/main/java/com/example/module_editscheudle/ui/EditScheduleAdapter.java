package com.example.module_editscheudle.ui;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_editscheudle.R;
import com.example.module_editscheudle.model.schedule;

import java.util.ArrayList;
import java.util.HashMap;

public class EditScheduleAdapter extends RecyclerView.Adapter<EditScheduleViewHolder>{
    ArrayList<schedule> scheduleArrayList;
    HashMap<String , Boolean> stringscheduleHashMap;
    Typeface typeface;

    public EditScheduleAdapter(ArrayList<schedule> scheduleArrayList , HashMap<String , Boolean> stringscheduleHashMap , Typeface typeface){
        this.stringscheduleHashMap = stringscheduleHashMap;
        this.scheduleArrayList =scheduleArrayList;
        this.typeface = typeface;
    }
    @NonNull
    @Override
    public EditScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate( R.layout.layout_recyclerview_schedule ,parent, false);
        EditScheduleViewHolder editScheduleViewHolder = new EditScheduleViewHolder(view);
        return editScheduleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditScheduleViewHolder holder, int position) {
        //当前Item
        schedule sc = scheduleArrayList.get(position);
        //更新UI
        holder.edit_schedule_image.setImageResource(sc.getImage());
        holder.edit_schedule_name.setText(sc.getText());
        holder.edit_schedule_name.setTypeface(typeface);
        if(sc.getComplete()){
            //已经完成
            holder.edit_schedule_right_image.setImageResource(R.drawable.editschedule_image_gouxuan);
        }else{
            holder.edit_schedule_right_image.setImageResource(R.drawable.editschedule_image_weigouxuan);
        }

        //        for(int i=0 ; i<curArrayList.size() ; i++){
//            if(curArrayList.get(i).getText().equals(sc.getText())){
//                //holder.edit_schedule_right_image.setImageResource(R.drawable.);
//            }
//        }
    }

    @Override
    public int getItemCount() {
        return scheduleArrayList.size();
    }
}
class EditScheduleViewHolder extends RecyclerView.ViewHolder{
    ImageView edit_schedule_image;
    TextView edit_schedule_name;
    ImageView edit_schedule_right_image;
    public EditScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        edit_schedule_image = itemView.findViewById(R.id.editschedule_recyclerview_image);
        edit_schedule_name = itemView.findViewById(R.id.editschedule_recyclerview_textview);
        edit_schedule_right_image = itemView.findViewById(R.id.editschedule_recyclerview_right_press);
    }
}
