package com.example.module_challenge.fragments;

import android.annotation.SuppressLint;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;
import com.example.module_challenge.logic.data.challenge_Database;
import com.example.module_challenge.logic.data.challenge_data;
import com.example.module_challenge.logic.data.challenge_data_dao;
import com.example.module_challenge.logic.model.ChallengePunch;
import com.example.module_challenge.ui.fragments_recyclerview.FirstFragmentAdapter;
import com.example.module_challenge.ui.fragments_recyclerview.FourthFragmentAdapter;
import com.example.module_challenge.ui.fragments_recyclerview.SecondFragmentAdapter;
import com.example.module_challenge.ui.fragments_recyclerview.ThirdFragmentAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import eventbus.EventChallenge_CardActivity_Back;


//这是第一个挑战开始挑战后的打卡界面(之后几个类似，不会有功能导图)
//功能及实现：
//代码目录：
//  0.声明变量
//  1.初始化任务

@Route(path = "/challenge/Challenge_Fragment_FourthCard_carrying")
public class Challenge_Fragment_FourthCard_carrying extends Fragment {
    //0.声明变量
    TextView challenge_fourthCard_progress_carrying;//界面内的进度信息文本介绍
    ImageView challenge_fourthCard_back_carrying;//返回主界面
    challenge_Database database;//在主线程跑数据库(测试环境)
    private challenge_data_dao dao;
    RecyclerView challenge_fourthCard_recyclerview_carrying;//RecyclerView的适配器加载
    FourthFragmentAdapter fourthFragmentAdapter;
    List<ChallengePunch> punchList ;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //UI的初始化
        View  view = inflater.inflate(R.layout.fragment_fourth_card_carrying,container,false);
        challenge_fourthCard_progress_carrying = view.findViewById(R.id.challenge_fourthCard_progress_carrying);
        challenge_fourthCard_back_carrying = view.findViewById(R.id.challenge_fourthCard_back_carrying);
        challenge_fourthCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_fourthFragment_RecyclerView);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();
        challenge_data data = dao.getChallengeById(4);
        int progress = data.getProgress();
        double db = (double) (progress)/7;
        int num = (int)(db*100);
        challenge_fourthCard_progress_carrying.setText("已完成"+num+"%");

        //RecyclerView
        challenge_fourthCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_fourthFragment_RecyclerView);
        punchList = new ArrayList<>();
        initPunch();
        fourthFragmentAdapter = new FourthFragmentAdapter(punchList,challenge_fourthCard_progress_carrying);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        challenge_fourthCard_recyclerview_carrying.setAdapter(fourthFragmentAdapter);
        challenge_fourthCard_recyclerview_carrying.setLayoutManager(linearLayoutManager);

        //back_button
        challenge_fourthCard_back_carrying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
                EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
            }
        });


        return view;
    }



    //1.初始化任务
    public void initPunch(){
        //第一个是0
        punchList.add(new ChallengePunch("第一天",R.drawable.challenge_cardfragment_carrying_yuga,"学习一段瑜伽","瑜伽在庭院,斜阳最丹颜"));
        punchList.add(new ChallengePunch("第二天",R.drawable.challenge_cardfragment_carrying_tea,"来一份安静的下午茶","西江水清江石老,石上生茶如凤爪"));
        punchList.add(new ChallengePunch("第三天",R.drawable.challenge_cardfragment_carrying_walk,"一个人去公园散散步","晚来天气好,散步中门前"));
        punchList.add(new ChallengePunch("第四天",R.drawable.challenge_cardfragment_carrying_yuga,"学习一段瑜伽","瑜伽在庭院,斜阳最丹颜" ));
        punchList.add(new ChallengePunch("第五天",R.drawable.challenge_cardfragment_carrying_tea,"来一份安静的下午茶","西江水清江石老,石上生茶如凤爪"));
        punchList.add(new ChallengePunch("第六天",R.drawable.challenge_cardfragment_carrying_sleep,"在纯音乐的陪伴下进行午休","天高云淡一杯茶,午睡梦回精神佳"));
        punchList.add(new ChallengePunch("第七天",R.drawable.challenge_cardfragment_carrying_walk,"一个人去公园散散步","晚来天气好,散步中门前"));


    }
}
