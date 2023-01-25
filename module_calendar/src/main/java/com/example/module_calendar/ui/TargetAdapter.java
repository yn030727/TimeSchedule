package com.example.module_calendar.ui;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_calendar.R;

import java.util.List;

public class TargetAdapter extends RecyclerView.Adapter<TargetViewHolder>{
    List<String> list ;
    public TargetAdapter(List<String> list){
        Log.d("Ning_Module_Calendar","TargetAdapter");
        this.list = list;
    }
    @NonNull
    @Override
    public TargetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.calendar_target_recyclerview,null);
        TargetViewHolder targetViewHolder = new TargetViewHolder(view);
        return targetViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TargetViewHolder holder, int position) {
        String str = list.get(position);
        holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class TargetViewHolder extends RecyclerView.ViewHolder{
    TextView textView;
    public TargetViewHolder(@NonNull View itemView) {

        super(itemView);
        textView = itemView.findViewById(R.id.calendarView_target_text);
    }
}
