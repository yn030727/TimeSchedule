package com.example.module_honor.ui;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_honor.R;
import com.example.module_honor.model.symbol.ChallengeHonor;

import java.util.List;

public class ChallengeProgressAdapter extends  RecyclerView.Adapter<ChallengeProgressViewHolder>{
    List<ChallengeHonor> challengeHonorList;
    Typeface typeface;
    int challenges[];
    int progress;
    public ChallengeProgressAdapter(List<ChallengeHonor> challengeHonorList , Typeface typeface , int challenges[] , int progress){
        this.challengeHonorList = challengeHonorList;
        this.typeface = typeface;
        this.challenges = challenges;
        this.progress = progress;
    }
    @NonNull
    @Override
    public ChallengeProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext() , R.layout.layout_honor_recyclerview_consecutiveday,null);
        ChallengeProgressViewHolder challengeProgressViewHolder = new ChallengeProgressViewHolder(view);
        return challengeProgressViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeProgressViewHolder holder, int position) {
        ChallengeHonor challengeHonor = challengeHonorList.get(position);
        holder.name.setText(challengeHonor.getChallenge_honor_name());
        holder.name.setTypeface(typeface);
        //根据是否完成当前挑战改变图片
        if(challenges[position] == 1){
            holder.imageView.setImageResource(R.drawable.honor_fragment_challengeprogress_image_complete);
            holder.constraintLayout.setBackgroundResource(R.drawable.honor_layout_shape5);
        }else {
            //没有完成
            holder.imageView.setImageResource(challengeHonor.getChallenge_honor_image());
        }

    }

    @Override
    public int getItemCount() {
        return challengeHonorList.size();
    }
}
class ChallengeProgressViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    ImageView imageView;
    View constraintLayout ;
    public ChallengeProgressViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.honor_fragment_consecutiveDay_recyclerView_name);
        imageView = itemView.findViewById(R.id.honor_fragment_consecutiveDay_recyclerView_image);
        constraintLayout = itemView.findViewById(R.id.honor_fragment_consecutiveDay_recyclerView_layout);
    }
}

