package com.example.module_challenge.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;

import org.greenrobot.eventbus.EventBus;

import eventbus.EventChallenge_CardActivity_Back;

@Route(path = "/challenge/ChallengeFragment_SecondCard")
public class ChallengeFragment_SecondCard extends Fragment implements View.OnClickListener {
    TextView SecondCard_Saying;
    ImageView challenge_secondCard_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_card,container,false);
        //设置瘦金体
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");

        SecondCard_Saying = view.findViewById(R.id.challenge_secondCard_saying);
        SecondCard_Saying.setTypeface(typeface);
        challenge_secondCard_back = view.findViewById(R.id.challenge_secondCard_back);

        challenge_secondCard_back.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.challenge_secondCard_back){
            //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
            EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
        }
    }
}
