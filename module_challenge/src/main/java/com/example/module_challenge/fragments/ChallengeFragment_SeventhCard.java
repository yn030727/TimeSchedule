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

@Route(path = "/challenge/ChallengeFragment_SeventhCard")
public class ChallengeFragment_SeventhCard extends Fragment implements View.OnClickListener {
    TextView seventhSaying;
    ImageView challenge_seventhCard_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seventh_card,container,false);
        seventhSaying = view.findViewById(R.id.challenge_seventhCard_saying);
        challenge_seventhCard_back = view.findViewById(R.id.challenge_seventhCard_back);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        seventhSaying.setTypeface(typeface);
        challenge_seventhCard_back.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.challenge_seventhCard_back){
            //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
            EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
        }
    }
}
