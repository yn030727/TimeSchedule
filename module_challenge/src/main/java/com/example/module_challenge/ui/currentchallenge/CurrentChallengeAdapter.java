package com.example.module_challenge.ui.currentchallenge;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.R;
import com.example.module_challenge.logic.model.CurrentChallengeCard;

import java.util.List;

public class CurrentChallengeAdapter extends RecyclerView.Adapter<CurrentChallengeViewHolder>{
    List<CurrentChallengeCard> currentChallengeCardList;
    Typeface typeface;
    public CurrentChallengeAdapter(List<CurrentChallengeCard> list , Typeface typeface){
        currentChallengeCardList = list;
        this.typeface = typeface;
    }
    @NonNull
    @Override
    public CurrentChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.layout_challenge_currentcard,null);
        CurrentChallengeViewHolder currentChallengeViewHolder = new CurrentChallengeViewHolder(view);
        return currentChallengeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentChallengeViewHolder holder, int position) {
        CurrentChallengeCard card = currentChallengeCardList.get(position);

        holder.cardName.setText(card.getChallenge_name());
        holder.cardSaying.setText(card.getChallenge_saying());
        holder.cardSaying.setTypeface(typeface);
        //更新控件的外观
    }

    @Override
    public int getItemCount() {
        return currentChallengeCardList.size();
    }
}
class CurrentChallengeViewHolder extends RecyclerView.ViewHolder{
    //获取控件的实例
    TextView cardName;
    TextView cardSaying;
    ImageView rightImage;
    public CurrentChallengeViewHolder(@NonNull View itemView) {
        super(itemView);
        cardName = itemView.findViewById(R.id.challenge_current_name);
        cardSaying = itemView.findViewById(R.id.challenge_current_saying);
        rightImage = itemView.findViewById(R.id.challenge_card_rightImage);
    }
}
