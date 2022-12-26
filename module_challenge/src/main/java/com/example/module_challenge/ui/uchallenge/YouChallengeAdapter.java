package com.example.module_challenge.ui.uchallenge;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.logic.model.YouChallengeCard;

//这是已经接下挑战的适配器
public class YouChallengeAdapter extends RecyclerView.Adapter<YouChallengeViewHolder>{


    public YouChallengeAdapter(){}

    @NonNull
    @Override
    public YouChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull YouChallengeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class YouChallengeViewHolder extends RecyclerView.ViewHolder{

    public YouChallengeViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
