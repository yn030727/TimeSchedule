package com.example.module_challenge.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;
import com.example.module_challenge.model.data.challenge_Database;
import com.example.module_challenge.model.data.challenge_data;
import com.example.module_challenge.model.data.challenge_data_dao;
import com.example.module_challenge.model.symbol.ChallengePunch;
import com.example.module_challenge.ui.fragments_recyclerview.FirstFragmentAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import eventbus.EventChallenge_CardActivity_Back;


//这是第一个挑战开始挑战后的打卡界面(之后几个类似，不会有功能导图)
//功能及实现：
//代码目录：
//  0.声明变量
//  1.初始化任务

@Route(path = "/challenge/Challenge_Fragment_FirstCard_carrying")
public class Challenge_Fragment_FirstCard_carrying extends Fragment {
    //0.声明变量
    TextView challenge_firstCard_progress_carrying;//界面内的进度信息文本介绍
    ImageView challenge_firstCard_back_carrying;//返回主界面
    challenge_Database database;//在主线程跑数据库(测试环境)
    private challenge_data_dao dao;
    RecyclerView challenge_firstCard_recyclerview_carrying;//RecyclerView的适配器加载
    FirstFragmentAdapter firstFragmentAdapter;
    List<ChallengePunch> punchList ;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //UI的初始化
        View  view = inflater.inflate(R.layout.fragment_first_card_carrying,container,false);
        challenge_firstCard_progress_carrying = view.findViewById(R.id.challenge_firstCard_progress_carrying);
        challenge_firstCard_back_carrying = view.findViewById(R.id.challenge_firstCard_back_carrying);
        challenge_firstCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_firstFragment_RecyclerView);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();
        challenge_data data = dao.getChallengeById(1);
        int progress = data.getProgress();
        double db = (double) (progress)/7;
        int num = (int)(db*100);
        challenge_firstCard_progress_carrying.setText("已完成"+num+"%");

        //RecyclerView
        challenge_firstCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_firstFragment_RecyclerView);
        punchList = new ArrayList<>();
        initPunch();
        firstFragmentAdapter = new FirstFragmentAdapter(punchList,challenge_firstCard_progress_carrying);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        challenge_firstCard_recyclerview_carrying.setAdapter(firstFragmentAdapter);
        challenge_firstCard_recyclerview_carrying.setLayoutManager(linearLayoutManager);

        //back_button
        challenge_firstCard_back_carrying.setOnClickListener(new View.OnClickListener() {
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
        //获取进度
        int day = dao.getChallengeById(1).progress;
        //第一个是0
        punchList.add(new ChallengePunch("第一天",R.drawable.challenge_cardfragment_carrying_water,"睡醒后来一杯水","何时同一瓢,饮水心亦足"));
        punchList.add(new ChallengePunch("第二天",R.drawable.challenge_cardfragment_carrying_water,"睡醒后来一杯水","何时同一瓢,饮水心亦足"));
        punchList.add(new ChallengePunch("第三天",R.drawable.challenge_cardfragment_carrying_food,"来一顿健康的朝食","且作吴羹助早餐,饱卧晴檐曝寒背"));
        punchList.add(new ChallengePunch("第四天",R.drawable.challenge_cardfragment_carrying_exercise,"要开始进行晨练了","东方欲晓,晨练催吾早" ));
        punchList.add(new ChallengePunch("第五天",R.drawable.challenge_cardfragment_carrying_water,"睡醒后来一杯水","何时同一瓢,饮水心亦足"));
        punchList.add(new ChallengePunch("第六天",R.drawable.challenge_cardfragment_carrying_food,"来一顿健康的朝食","且作吴羹助早餐,饱卧晴檐曝寒背"));
        punchList.add(new ChallengePunch("第七天",R.drawable.challenge_cardfragment_carrying_deliciousfood,"从早晨开始享受这一天","采菊东篱下,悠然见南山"));


    }
}
