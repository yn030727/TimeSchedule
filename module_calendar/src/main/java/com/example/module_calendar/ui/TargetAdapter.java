package com.example.module_calendar.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TargetAdapter extends RecyclerView.Adapter<TargetViewHolder>{
    @NonNull
    @Override
    public TargetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TargetViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class TargetViewHolder extends RecyclerView.ViewHolder{
    public TargetViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
