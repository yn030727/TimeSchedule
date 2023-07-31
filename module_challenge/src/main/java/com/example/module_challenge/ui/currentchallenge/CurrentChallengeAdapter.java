package com.example.module_challenge.ui.currentchallenge;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.R;
import com.example.module_challenge.model.symbol.CurrentChallengeCard;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import eventbus.EventChallengeCard;

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
        int number = position;
        holder.cardName.setText(card.getChallenge_name());
        holder.cardSaying.setText(card.getChallenge_saying());
        holder.cardSaying.setTypeface(typeface);
        holder.imageView.setImageResource(card.getChallenge_image());
        //对每一个Item的点击事件,跳转到不同的界面
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ning", number+"");
                System.out.println(number);
                //String path = card.getTo_challenge_activity();
                //获取要跳转到响应的界面
                //Fragment fragment = (Fragment) ARouter.getInstance().build(path).navigation();
                //ARouter.getInstance().build("/challenge/ChallengeActivity").navigation();
                //在这里遇到了一个bug,退出到后台之后点击事件依然生效，但是黏性事件无法进行再次的发送
                //D/EventBus: No subscribers registered for event class eventbus.EventChallengeCard
                //D/EventBus: No subscribers registered for event class org.greenrobot.eventbus.NoSubscriberEvent
                EventBus.getDefault().postSticky(new EventChallengeCard(true,number));
            }
        });
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
    ImageView imageView;
    Button button;
    public CurrentChallengeViewHolder(@NonNull View itemView) {
        super(itemView);
        cardName = itemView.findViewById(R.id.challenge_current_name);
        cardSaying = itemView.findViewById(R.id.challenge_current_saying);
        //rightImage = itemView.findViewById(R.id.challenge_card_rightImage);
        imageView = itemView.findViewById(R.id.challenge_current_imageView);
        button = itemView.findViewById(R.id.challenge_currentcard_button);
    }
}
