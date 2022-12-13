package com.example.module_challenge.ui.currentchallenge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.R;
import com.example.module_challenge.logic.model.CurrentChallengeCard;

import java.util.List;

public class CurrentChallengeAdapter extends RecyclerView.Adapter<CurrentChallengeViewHolder>{
    List<CurrentChallengeCard> currentChallengeCardList;
    public CurrentChallengeAdapter(List<CurrentChallengeCard> list){
        currentChallengeCardList = list;
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
        //更新控件的外观
    }

    @Override
    public int getItemCount() {
        return currentChallengeCardList.size();
    }
}
class CurrentChallengeViewHolder extends RecyclerView.ViewHolder{

    public CurrentChallengeViewHolder(@NonNull View itemView) {
        //获取控件的实例

        super(itemView);
    }
}
