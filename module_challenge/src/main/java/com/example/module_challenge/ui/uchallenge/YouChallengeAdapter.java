package com.example.module_challenge.ui.uchallenge;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.logic.model.YouChallengeCard;

import java.util.List;

//这是已经接下挑战的适配器
public class YouChallengeAdapter extends RecyclerView.Adapter<YouChallengeViewHolder>{

    List<YouChallengeCard> youChallengeCardList;
    Typeface typeface;
    public YouChallengeAdapter(List<YouChallengeCard> youChallengeCardList , Typeface typeface){
        this.youChallengeCardList = youChallengeCardList;
        this.typeface = typeface;
    }

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
        return youChallengeCardList.size();
    }
}
class YouChallengeViewHolder extends RecyclerView.ViewHolder{

    public YouChallengeViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
