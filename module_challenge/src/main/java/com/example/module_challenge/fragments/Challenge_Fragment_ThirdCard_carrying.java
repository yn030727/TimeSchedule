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
import com.example.module_challenge.ui.fragments_recyclerview.ThirdFragmentAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import eventbus.EventChallenge_CardActivity_Back;


//这是第一个挑战开始挑战后的打卡界面(之后几个类似，不会有功能导图)
//功能及实现：
//代码目录：
//  0.声明变量
//  1.初始化任务

@Route(path = "/challenge/Challenge_Fragment_ThirdCard_carrying")
public class Challenge_Fragment_ThirdCard_carrying extends Fragment {
    //0.声明变量
    TextView challenge_thirdCard_progress_carrying;//界面内的进度信息文本介绍
    ImageView challenge_thirdCard_back_carrying;//返回主界面
    challenge_Database database;//在主线程跑数据库(测试环境)
    private challenge_data_dao dao;
    RecyclerView challenge_thirdCard_recyclerview_carrying;//RecyclerView的适配器加载
    ThirdFragmentAdapter thirdFragmentAdapter;
    List<ChallengePunch> punchList ;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //UI的初始化
        View  view = inflater.inflate(R.layout.fragment_third_card_carrying,container,false);
        challenge_thirdCard_progress_carrying = view.findViewById(R.id.challenge_thirdCard_progress_carrying);
        challenge_thirdCard_back_carrying = view.findViewById(R.id.challenge_thirdCard_back_carrying);
        challenge_thirdCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_thirdFragment_RecyclerView);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();
        challenge_data data = dao.getChallengeById(3);
        int progress = data.getProgress();
        double db = (double) (progress)/7;
        int num = (int)(db*100);
        challenge_thirdCard_progress_carrying.setText("已完成"+num+"%");

        //RecyclerView
        challenge_thirdCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_thirdFragment_RecyclerView);
        punchList = new ArrayList<>();
        initPunch();
        thirdFragmentAdapter = new ThirdFragmentAdapter(punchList,challenge_thirdCard_progress_carrying);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        challenge_thirdCard_recyclerview_carrying.setAdapter(thirdFragmentAdapter);
        challenge_thirdCard_recyclerview_carrying.setLayoutManager(linearLayoutManager);

        //back_button
        challenge_thirdCard_back_carrying.setOnClickListener(new View.OnClickListener() {
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
        punchList.add(new ChallengePunch("第一天",R.drawable.challenge_cardfragment_carrying_wenhao,"礼貌的向每个人问好","君子以仁存心，以礼存心"));
        punchList.add(new ChallengePunch("第二天",R.drawable.challenge_cardfragment_carrying_music,"听你最爱的歌","此曲只应天上有,人间能得几回闻"));
        punchList.add(new ChallengePunch("第三天",R.drawable.challenge_cardfragment_carrying_study,"看一本值得看的好书","读万卷书,行万里路"));
        punchList.add(new ChallengePunch("第四天",R.drawable.challenge_cardfragment_carrying_study,"看一本值得看的好书","读万卷书,行万里路" ));
        punchList.add(new ChallengePunch("第五天",R.drawable.challenge_cardfragment_carrying_study,"看一本值得看的好书","读万卷书,行万里路"));
        punchList.add(new ChallengePunch("第六天",R.drawable.challenge_cardfragment_carrying_math,"感受高数带来的快乐吧","圆周率几何,却道律平仄"));
        punchList.add(new ChallengePunch("第七天",R.drawable.challenge_cardfragment_carrying_computer,"学习你想学的新知识","路漫漫其修远兮，吾将上下而求索"));


    }
}
