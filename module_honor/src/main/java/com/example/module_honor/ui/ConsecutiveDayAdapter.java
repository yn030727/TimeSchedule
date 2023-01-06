package com.example.module_honor.ui;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_honor.R;
import com.example.module_honor.logic.model.ConsecutiveDay;

import java.util.List;

public class ConsecutiveDayAdapter  extends RecyclerView.Adapter<ConsecutiveDayViewHolder>{
    List<ConsecutiveDay> consecutiveDayList;
    Typeface typeface;
    public ConsecutiveDayAdapter(List<ConsecutiveDay> consecutiveDayList, Typeface typeface){
        this.consecutiveDayList = consecutiveDayList;
        this.typeface = typeface;
    }
    @NonNull
    @Override
    public ConsecutiveDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.layout_honor_recyclerview_consecutiveday,null);
        ConsecutiveDayViewHolder consecutiveDayViewHolder = new ConsecutiveDayViewHolder(view);
        return consecutiveDayViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsecutiveDayViewHolder holder, int position) {
        Log.d("Ning_honor","ConsecutiveDayAdapter get position : " + position);
        ConsecutiveDay consecutiveDay = consecutiveDayList.get(position);
        holder.name.setText(consecutiveDay.getConsecutiveDay_name());
        holder.name.setTypeface(typeface);
        holder.description.setText(consecutiveDay.getConsecutiveDay_description());
        holder.imageview.setImageResource(consecutiveDay.getConsecutiveDay_image());
    }

    @Override
    public int getItemCount() {
        return consecutiveDayList.size();
    }
}
class ConsecutiveDayViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView description;
    ImageView imageview;
    public ConsecutiveDayViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.honor_fragment_consecutiveDay_recyclerView_name);
        description = itemView.findViewById(R.id.honor_fragment_consecutiveDay_recyclerView_description);
        imageview = itemView.findViewById(R.id.honor_fragment_consecutiveDay_recyclerView_image);
    }
}
