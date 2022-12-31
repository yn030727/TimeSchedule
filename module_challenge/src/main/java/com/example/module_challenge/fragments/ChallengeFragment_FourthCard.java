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

@Route(path = "/challenge/ChallengeFragment_FourthCard")
public class ChallengeFragment_FourthCard extends Fragment implements View.OnClickListener {
    TextView fourthSaying;
    ImageView challenge_fourthCard_back;
    Button challenge_fourthCard_button;
    //当前挑战项目是否被挑战，如果已经点击了同意挑战，那么就将这个值变为true
    //当挑战结束后，再将这个值重新变为false
    Boolean challenge_fourthCard_status = false;
    //在主线程跑数据库(测试环境)
    challenge_Database database;
    private challenge_data_dao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_card,container,false);
        //设置瘦金体
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");

        fourthSaying = view.findViewById(R.id.challenge_fourthCard_saying);
        fourthSaying.setTypeface(typeface);
        challenge_fourthCard_back = view.findViewById(R.id.challenge_fourthCard_back);
        challenge_fourthCard_button = view.findViewById(R.id.challenge_fourthCardCard_button);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();

        challenge_fourthCard_back.setOnClickListener(this);
        challenge_fourthCard_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.challenge_fourthCard_back) {
            //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
            EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
        }else if(v.getId() == R.id.challenge_fourthCardCard_button){
            challenge_fourthCard_status = true;
            Log.d("Ning_Challenge", "fourthCard : " + challenge_fourthCard_status);

            challenge_data data = dao.getChallengeById(4);
            if(data != null){
                Log.d("Ning_Room", "FourthCardFragment QueryById id： " + data.id);
                Log.d("Ning_Room", "FourthCardFragment QueryById challenge： " + data.challenge);
                Log.d("Ning_Room", "FourthCardFragment QueryById progress： " + data.progress);
                Log.d("Ning_Room", "FourthCardFragment QueryById complete： " + data.complete);
            }
            if(data == null){
                //3.没有
                dao.insertChallenge_data(new challenge_data(4,1,0,0));
                Toast.makeText(getContext(),"您接下了挑战，客官",Toast.LENGTH_SHORT).show();
            }else if(data.id == 4){
                if(data.challenge == 0){
                    //4.重新挑战
                    dao.updateChallenge_data(new challenge_data(4,1,0,1));
                    Toast.makeText(getContext(),"您再次接下了挑战",Toast.LENGTH_SHORT).show();
                }else if(data.challenge == 1){
                    //5.已经在挑战中了
                    Toast.makeText(getContext(),"不需要重复接下挑战哦，小主！",Toast.LENGTH_SHORT).show();
                }
            }else{
                Log.d("Ning_Room","Fourth data id is wrong");
            }
        }
    }
}
