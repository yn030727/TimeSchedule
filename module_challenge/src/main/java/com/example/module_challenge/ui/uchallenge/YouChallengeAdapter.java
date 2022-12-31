package com.example.module_challenge.ui.uchallenge;

import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.R;
import com.example.module_challenge.logic.model.YouChallengeCard;

import java.util.List;

//这是已经接下挑战的适配器
public class YouChallengeAdapter extends RecyclerView.Adapter<YouChallengeViewHolder>{

    List<YouChallengeCard> youChallengeCardList;
    public YouChallengeAdapter(List<YouChallengeCard> youChallengeCardList ){
        this.youChallengeCardList = youChallengeCardList;
    }

    @NonNull
    @Override
    public YouChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.layout_challenge_youcard,null);
        YouChallengeViewHolder viewHolder = new YouChallengeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YouChallengeViewHolder holder, int position) {
        YouChallengeCard card = youChallengeCardList.get(position);
        holder.your_card_name.setText(card.getU_challenge_name());
        holder.your_image.setImageResource(card.getU_challenge_image());
        holder.your_progress.setProgress(card.getU_challenge_progress());
    }

    @Override
    public int getItemCount() {
        return youChallengeCardList.size();
    }
}
class YouChallengeViewHolder extends RecyclerView.ViewHolder{

    TextView your_card_name;
    ProgressBar your_progress;
    ImageView your_image;
    public YouChallengeViewHolder(@NonNull View itemView) {
        super(itemView);
        your_card_name = itemView.findViewById(R.id.challenge_yourName);
        your_progress = itemView.findViewById(R.id.challenge_YourProgressBar);
        your_image = itemView.findViewById(R.id.challenge_yourImageView);
    }
}
