package com.example.module_challenge.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;

import org.greenrobot.eventbus.EventBus;

import eventbus.EventChallenge_CardActivity_Back;


@Route(path = "/challenge/ChallengeFragment_SixthCard")
public class ChallengeFragment_SixthCard extends Fragment implements View.OnClickListener {
    TextView sixthSaying;
    ImageView challenge_sixthCard_back;
    Button challenge_sixthCard_button;
    //当前挑战项目是否被挑战，如果已经点击了同意挑战，那么就将这个值变为true
    //当挑战结束后，再将这个值重新变为false
    Boolean challenge_sixthCard_status = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sixth_card,container,false);
        sixthSaying = view.findViewById(R.id.challenge_sixthCard_saying);
        challenge_sixthCard_back = view.findViewById(R.id.challenge_sixthCard_back);
        challenge_sixthCard_button = view.findViewById(R.id.challenge_sixthCard_button);


        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        sixthSaying.setTypeface(typeface);
        challenge_sixthCard_back.setOnClickListener(this);
        challenge_sixthCard_button.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.challenge_sixthCard_back){
            //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
            EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
        }else if(v.getId() == R.id.challenge_sixthCard_button){
            challenge_sixthCard_status = true;
            Log.d("Ning_Challenge" , "sixthCard : " + challenge_sixthCard_status);
        }
    }
}
