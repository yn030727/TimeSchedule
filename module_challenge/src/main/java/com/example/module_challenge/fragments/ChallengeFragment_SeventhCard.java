package com.example.module_challenge.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;
import com.example.module_challenge.logic.data.challenge_Database;
import com.example.module_challenge.logic.data.challenge_data;
import com.example.module_challenge.logic.data.challenge_data_dao;

import org.greenrobot.eventbus.EventBus;

import eventbus.EventChallenge_CardActivity_Back;

@Route(path = "/challenge/ChallengeFragment_SeventhCard")
public class ChallengeFragment_SeventhCard extends Fragment implements View.OnClickListener {
    TextView seventhSaying;
    ImageView challenge_seventhCard_back;
    Button challenge_seventhCard_button;
    //当前挑战项目是否被挑战，如果已经点击了同意挑战，那么就将这个值变为true
    //当挑战结束后，再将这个值重新变为false
    Boolean challenge_seventhCard_status;
    //在主线程跑数据库(测试环境)
    challenge_Database database;
    private challenge_data_dao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seventh_card,container,false);
        seventhSaying = view.findViewById(R.id.challenge_seventhCard_saying);
        challenge_seventhCard_back = view.findViewById(R.id.challenge_seventhCard_back);
        challenge_seventhCard_button = view.findViewById(R.id.challenge_seventhCard_button);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        seventhSaying.setTypeface(typeface);
        challenge_seventhCard_back.setOnClickListener(this);
        challenge_seventhCard_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.challenge_seventhCard_back){
            //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
            EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
        }else if(v.getId() == R.id.challenge_seventhCard_button){
            challenge_seventhCard_status = true;
            Log.d("Ning_Challenge", "seventhCard : " + challenge_seventhCard_status);

            challenge_data data = dao.getChallengeById(7);
            if(data != null){
                Log.d("Ning_Room", "SeventhCardFragment QueryById id： " + data.id);
                Log.d("Ning_Room", "SeventhCardFragment QueryById challenge： " + data.challenge);
                Log.d("Ning_Room", "SeventhCardFragment QueryById progress： " + data.progress);
                Log.d("Ning_Room", "SeventhCardFragment QueryById complete： " + data.complete);
            }
            if(data == null){
                //3.没有
                dao.insertChallenge_data(new challenge_data(7,1,0,0));
            }else if(data.id == 7){
                if(data.challenge == 0){
                    //4.重新挑战
                    dao.updateChallenge_data(new challenge_data(7,1,0,1));
                }else if(data.challenge == 1){
                    //5.已经在挑战中了
                    Toast.makeText(getContext(),"不需要重复接下挑战哦，小主！",Toast.LENGTH_SHORT).show();
                }
            }else{
                Log.d("Ning_Room","Seventh data id is wrong");
            }
        }
    }
}
